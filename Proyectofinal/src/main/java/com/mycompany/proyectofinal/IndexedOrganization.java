import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class IndexedOrganization {

    static final int NAME_CHARS = 50;
    static final int MAJOR_CHARS = 30;

    static final int DATA_RECORD_SIZE = (NAME_CHARS + MAJOR_CHARS) * 2 + 4 + 1;

    static final String DATA_FILE = "students.dat";
    static final String INDEX_FILE = "students.idx";

    static Map<Integer, Long> indexMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        loadIndex();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Organizacion Indexada (Java) ===");
            System.out.println("1) Agregar estudiante");
            System.out.println("2) Buscar por ID");
            System.out.println("3) Actualizar por ID");
            System.out.println("4) Eliminar por ID");
            System.out.println("5) Listar todos");
            System.out.println("6) Salir");
            System.out.print("Opcion: ");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1":
                    addStudent(sc);
                    break;
                case "2":
                    searchStudent(sc);
                    break;
                case "3":
                    updateStudent(sc);
                    break;
                case "4":
                    deleteStudent(sc);
                    break;
                case "5":
                    listAll();
                    break;
                case "6":
                    saveIndex();
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    static void loadIndex() throws IOException {
        indexMap.clear();
        File f = new File(INDEX_FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0]);
                    long pos = Long.parseLong(parts[1]);
                    indexMap.put(id, pos);
                }
            }
        }
    }

    static void saveIndex() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INDEX_FILE, StandardCharsets.UTF_8))) {
            for (Map.Entry<Integer, Long> e : indexMap.entrySet()) {
                bw.write(e.getKey() + "|" + e.getValue());
                bw.newLine();
            }
        }
    }

    static long getDataFileLength() {
        File f = new File(DATA_FILE);
        if (!f.exists()) return 0L;
        return f.length();
    }

    static void addStudent(Scanner sc) throws IOException {
        System.out.print("ID (entero): ");
        int id = Integer.parseInt(sc.nextLine().trim());
        if (indexMap.containsKey(id)) {
            System.out.println("ID ya existe.");
            return;
        }

        System.out.print("Nombre: ");
        String name = sc.nextLine();
        System.out.print("Edad: ");
        int age = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Carrera: ");
        String major = sc.nextLine();

        long offset = getDataFileLength();

        try (RandomAccessFile raf = new RandomAccessFile(DATA_FILE, "rw")) {
            raf.seek(offset);
            writeFixedString(raf, name, NAME_CHARS);
            raf.writeInt(age);
            writeFixedString(raf, major, MAJOR_CHARS);
            raf.writeBoolean(false);
        }

        indexMap.put(id, offset);
        saveIndex();
        System.out.println("Estudiante agregado.");
    }

    static void searchStudent(Scanner sc) throws IOException {
        System.out.print("ID a buscar: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        if (!indexMap.containsKey(id)) {
            System.out.println("No existe registro con ese ID.");
            return;
        }
        long pos = indexMap.get(id);
        Record rec = readRecordAt(pos);
        if (rec.isDeleted) {
            System.out.println("Registro eliminado.");
        } else {
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + rec.name);
            System.out.println("Edad: " + rec.age);
            System.out.println("Carrera: " + rec.major);
        }
    }

    static void updateStudent(Scanner sc) throws IOException {
        System.out.print("ID a actualizar: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        if (!indexMap.containsKey(id)) {
            System.out.println("ID no encontrado.");
            return;
        }
        long pos = indexMap.get(id);
        Record rec = readRecordAt(pos);
        if (rec.isDeleted) {
            System.out.println("Registro eliminado.");
            return;
        }

        System.out.println("Nombre actual: " + rec.name);
        System.out.print("Nuevo nombre (enter = mantener): ");
        String nName = sc.nextLine();
        if (nName.isBlank()) nName = rec.name;

        System.out.println("Edad actual: " + rec.age);
        System.out.print("Nueva edad (enter = mantener): ");
        String nAgeS = sc.nextLine();
        int nAge = nAgeS.isBlank() ? rec.age : Integer.parseInt(nAgeS);

        System.out.println("Carrera actual: " + rec.major);
        System.out.print("Nueva carrera (enter = mantener): ");
        String nMajor = sc.nextLine();
        if (nMajor.isBlank()) nMajor = rec.major;

        try (RandomAccessFile raf = new RandomAccessFile(DATA_FILE, "rw")) {
            raf.seek(pos);
            writeFixedString(raf, nName, NAME_CHARS);
            raf.writeInt(nAge);
            writeFixedString(raf, nMajor, MAJOR_CHARS);
            raf.writeBoolean(false);
        }

        System.out.println("Registro actualizado.");
    }

    static void deleteStudent(Scanner sc) throws IOException {
        System.out.print("ID a eliminar: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        if (!indexMap.containsKey(id)) {
            System.out.println("ID no encontrado.");
            return;
        }
        long pos = indexMap.get(id);
        Record rec = readRecordAt(pos);
        if (rec.isDeleted) {
            System.out.println("Registro ya eliminado.");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(DATA_FILE, "rw")) {
            raf.seek(pos + (NAME_CHARS * 2) + 4 + (MAJOR_CHARS * 2));
            raf.writeBoolean(true);
        }

        indexMap.remove(id);
        saveIndex();
        System.out.println("Registro eliminado.");
    }

    static void listAll() throws IOException {
        File f = new File(DATA_FILE);
        if (!f.exists()) {
            System.out.println("No hay datos.");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(DATA_FILE, "r")) {
            long total = raf.length() / DATA_RECORD_SIZE;
            System.out.println("Registros totales: " + total);
            for (int i = 0; i < total; i++) {
                long pos = (long) i * DATA_RECORD_SIZE;
                Record r = readRecordAt(pos);
                if (!r.isDeleted) {
                    System.out.println("-----------");
                    System.out.println("Offset: " + pos);
                    System.out.println("Nombre: " + r.name);
                    System.out.println("Edad: " + r.age);
                    System.out.println("Carrera: " + r.major);
                }
            }
        }
    }

    static void writeFixedString(RandomAccessFile raf, String text, int nChars) throws IOException {
        if (text == null) text = "";
        String trimmed = text.length() > nChars ? text.substring(0, nChars) : text;
        for (int i = 0; i < trimmed.length(); i++) {
            raf.writeChar(trimmed.charAt(i));
        }
        for (int i = trimmed.length(); i < nChars; i++) {
            raf.writeChar(0);
        }
    }

    static Record readRecordAt(long offset) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(DATA_FILE, "r")) {
            raf.seek(offset);
            char[] nameChars = new char[NAME_CHARS];
            for (int i = 0; i < NAME_CHARS; i++) {
                nameChars[i] = raf.readChar();
            }
            String name = new String(nameChars).trim();

            int age = raf.readInt();

            char[] majorChars = new char[MAJOR_CHARS];
            for (int i = 0; i < MAJOR_CHARS; i++) {
                majorChars[i] = raf.readChar();
            }
            String major = new String(majorChars).trim();

            boolean isDel = raf.readBoolean();
            return new Record(name, age, major, isDel);
        }
    }

    static class Record {
        String name;
        int age;
        String major;
        boolean isDeleted;

        Record(String name, int age, String major, boolean isDeleted) {
            this.name = name;
            this.age = age;
            this.major = major;
            this.isDeleted = isDeleted;
        }
    }
}