package com.mycompany.proyectofinal;
import java.util.*;

/**
 * Contiene la lógica del programa.
 * Se encarga de interactuar con el usuario y usar la clase ArchivoSecuencial
 * para guardar, buscar, ordenar, calcular promedios y exportar reportes.
 */
public class GestorAlumnos {
    private ArchivoSecuencial archivo = new ArchivoSecuencial();
    private Scanner sc = new Scanner(System.in);

    /**
     * Pide datos al usuario y guarda un nuevo alumno en el archivo secuencial.
     */
    public void agregarAlumno() {
        System.out.print("Nombre del alumno: ");
        String nombre = sc.nextLine();
        System.out.print("Promedio: ");
        double promedio = Double.parseDouble(sc.nextLine());

        Alumno alumno = new Alumno(nombre, promedio);
        archivo.guardarAlumno(alumno);
        System.out.println("Alumno guardado correctamente.");
    }

    /**
     * Muestra todos los alumnos en el orden en que fueron guardados.
     * Para cada uno indica si está aprobado o reprobado.
     */
    public void mostrarTodos() {
        List<Alumno> alumnos = archivo.leerAlumnos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (Alumno a : alumnos) {
                System.out.println("Nombre: " + a.getNombre() +
                        " | Promedio: " + a.getPromedio() +
                        " | " + (a.estaAprobado() ? "Aprobado" : "Reprobado"));
            }
        }
    }

    /**
     * Permite buscar un alumno por su nombre exacto.
     * Recorre el archivo de manera secuencial.
     */
    public void buscarPorNombre() {
        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = sc.nextLine();
        List<Alumno> alumnos = archivo.leerAlumnos();
        boolean encontrado = false;

        for (Alumno a : alumnos) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Nombre: " + a.getNombre() +
                        " | Promedio: " + a.getPromedio() +
                        " | " + (a.estaAprobado() ? "Aprobado" : "Reprobado"));
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("Alumno no encontrado.");
    }

    /**
     * Ordena y muestra los alumnos de manera alfabética (A-Z)
     * sin modificar el archivo original (solo ordena en memoria).
     */
    public void buscarPorOrdenAlfabetico() {
        List<Alumno> alumnos = archivo.leerAlumnos();
        alumnos.sort(Comparator.comparing(Alumno::getNombre));
        alumnos.forEach(a ->
                System.out.println(a.getNombre() + " | " + a.getPromedio() +
                        " | " + (a.estaAprobado() ? "Aprobado" : "Reprobado")));
    }

    /**
     * Ordena y muestra los alumnos según su promedio (de mayor a menor).
     * También es una ordenación en memoria.
     */
    public void buscarPorPromedio() {
        List<Alumno> alumnos = archivo.leerAlumnos();
        alumnos.sort(Comparator.comparingDouble(Alumno::getPromedio).reversed());
        alumnos.forEach(a ->
                System.out.println(a.getNombre() + " | " + a.getPromedio() +
                        " | " + (a.estaAprobado() ? "Aprobado" : "Reprobado")));
    }

    /**
     * Calcula y muestra el promedio general de todo el grupo.
     */
    public void calcularPromedioGeneral() {
        List<Alumno> alumnos = archivo.leerAlumnos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay datos.");
            return;
        }
        double suma = 0;
        for (Alumno a : alumnos) {
            suma += a.getPromedio();
        }
        double promedioGeneral = suma / alumnos.size();
        System.out.println("Promedio general del grupo: " + promedioGeneral);
    }

    /**
     * Genera un archivo "reporte.txt" con la información completa de todos los alumnos.
     * Es útil para respaldos o impresión.
     */
    public void exportarReporte() {
        List<Alumno> alumnos = archivo.leerAlumnos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay datos para exportar.");
            return;
        }

        try (Formatter f = new Formatter("reporte.txt")) {
            f.format("--- Reporte de Alumnos ---\n");
            for (Alumno a : alumnos) {
                f.format("%s | %.2f | %s\n",
                        a.getNombre(),
                        a.getPromedio(),
                        a.estaAprobado() ? "Aprobado" : "Reprobado");
            }
            System.out.println("Reporte exportado a 'reporte.txt'.");
        } catch (Exception e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }
}
