package com.mycompany.proyectofinal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BusquedaAlumnos {
    // Arreglo para almacenar alumnos (capacidad máxima 100)
    private static AlumnosSec[] alumnos = new AlumnosSec[100];
    private static int total = 0; // número actual de alumnos registrados

    public void ejecutar() {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            // Menú principal
            System.out.println("\n=== SISTEMA DE ALUMNOS ===");
            System.out.println("1. Registrar alumno");
            System.out.println("2. Buscar alumno por matrícula");
            System.out.println("3. Ver todos los alumnos");
            System.out.println("4. Dar de baja alumno");
            System.out.println("5. Ejemplo gráfico: Búsqueda secuencial");
            System.out.println("6. Qué es búsqueda secuencial");
            System.out.println("7. Volver al menu principal");
            System.out.print("Selecciona una opción: ");

            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Ingresa un número entre 1 y 7.");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            // Redirigir según la opción
            switch (opcion) {
                case 1 -> registrarAlumno(sc);
                case 2 -> buscarAlumno(sc);
                case 3 -> mostrarAlumnos();
                case 4 -> darDeBajaAlumno(sc);
                case 5 -> ejemploBusquedaSecuencial();
                case 6 -> explicarBusquedaSecuencial();
                case 7 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 7);

        sc.close();
    }

    // ---------------- MÉTODOS ----------------

    // Registrar un alumno con validaciones
    private static void registrarAlumno(Scanner sc) {
        if (total >= alumnos.length) {
            System.out.println("No se pueden agregar más alumnos.");
            return;
        }

        String matricula;
        while (true) {
            System.out.print("Ingrese la matrícula (Ej: ABC1234): ");
            matricula = sc.nextLine().trim().toUpperCase();

            // Validar estructura básica: 3 letras + 4 números
            if (!matricula.matches("^[A-Z]{3}\\d{4}$")) {
                System.out.println("La matrícula debe tener 3 letras seguidas de 4 números (Ej: ABC1234).");
                continue;
            }

            // Verificar si ya existe
            boolean existe = false;
            for (int i = 0; i < total; i++) {
                if (alumnos[i].getMatricula().equalsIgnoreCase(matricula)) {
                    existe = true;
                    break;
                }
            }

            if (existe) {
                System.out.println("Ya existe un alumno con esa matrícula. Ingresa otra.");
            } else {
                break;
            }
        }

        String nombre;
        while (true) {
            System.out.print("Ingrese el nombre (solo letras y espacios): ");
            nombre = sc.nextLine().trim();
            if (nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                break;
            } else {
                System.out.println("El nombre solo puede contener letras y espacios.");
            }
        }

        double promedio;
        while (true) {
            try {
                System.out.print("Ingrese el promedio (0 a 10): ");
                promedio = sc.nextDouble();
                sc.nextLine();
                if (promedio >= 0 && promedio <= 10) {
                    break;
                } else {
                    System.out.println("El promedio debe estar entre 0 y 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresa un valor numérico válido.");
                sc.next();
            }
        }

        alumnos[total++] = new AlumnosSec(matricula, nombre, promedio);
        System.out.println("Alumno registrado correctamente.");
    }

    // Buscar alumno por matrícula (con submenú para ver el proceso)
    private static void buscarAlumno(Scanner sc) {
        if (total == 0) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        System.out.print("Ingrese la matrícula a buscar: ");
        String buscada = sc.nextLine().trim().toUpperCase();

        // Submenú: elegir ver resultado o proceso
        System.out.println("\nOpciones de búsqueda:");
        System.out.println("1. Ver solo el resultado");
        System.out.println("2. Ver proceso paso a paso");
        System.out.print("Selecciona una opción: ");

        int eleccion = sc.nextInt();
        sc.nextLine();

        int comparaciones = 0;
        int indiceEncontrado = -1;

        for (int i = 0; i < total; i++) {
            comparaciones++;
            if (eleccion == 2) {
                System.out.println("Comparando " + alumnos[i].getMatricula() + " == " + buscada +
                        " ? -> " + (alumnos[i].getMatricula().equalsIgnoreCase(buscada) ? "sí" : "no"));
            }
            if (alumnos[i].getMatricula().equalsIgnoreCase(buscada)) {
                indiceEncontrado = i;
                break;
            }
        }

        if (indiceEncontrado != -1) {
            System.out.println("\nAlumno encontrado:");
            System.out.println(alumnos[indiceEncontrado]);
            System.out.println("Total de comparaciones: " + comparaciones);
        } else {
            System.out.println("\nNo existe ningún alumno con esa matrícula.");
            System.out.println("Total de comparaciones realizadas: " + comparaciones);
        }
    }

    // Mostrar todos los alumnos registrados
    private static void mostrarAlumnos() {
        if (total == 0) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        System.out.println("\n=== LISTA DE ALUMNOS ===");
        for (int i = 0; i < total; i++) {
            System.out.println((i + 1) + ". " + alumnos[i]);
        }
    }

    // Dar de baja alumno por matrícula (elimina del arreglo y recorre para no dejar
    // huecos)
    private static void darDeBajaAlumno(Scanner sc) {
        if (total == 0) {
            System.out.println("No hay alumnos registrados para dar de baja.");
            return;
        }

        System.out.print("Ingrese la matrícula del alumno a dar de baja: ");
        String baja = sc.nextLine().trim().toUpperCase();

        int indice = -1;
        for (int i = 0; i < total; i++) {
            if (alumnos[i].getMatricula().equalsIgnoreCase(baja)) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            // Mover todos los alumnos una posición a la izquierda
            for (int i = indice; i < total - 1; i++) {
                alumnos[i] = alumnos[i + 1];
            }
            alumnos[--total] = null; // liberar la última posición
            System.out.println("Alumno con matrícula " + baja + " dado de baja exitosamente.");
        } else {
            System.out.println("No se encontró ningún alumno con esa matrícula.");
        }
    }

    // Ejemplo gráfico con matrículas fijas
    private static void ejemploBusquedaSecuencial() {
        System.out.println("\n--- Ejemplo gráfico: Búsqueda secuencial ---");
        String[] arreglo = { "ABC1234", "DEF5678", "GHI9012", "JKL3456", "MNO7890" };
        String buscado = "JKL3456";

        System.out.print("Arreglo: [");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i]);
            if (i < arreglo.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");

        System.out.println("Queremos buscar: " + buscado);
        System.out.println("Proceso (comparaciones):");

        int comparaciones = 0;
        int indiceEncontrado = -1;

        for (int i = 0; i < arreglo.length; i++) {
            comparaciones++;
            System.out.println("Comparando " + arreglo[i] + " == " + buscado + " ? -> " +
                    (arreglo[i].equals(buscado) ? "sí" : "no"));
            if (arreglo[i].equals(buscado)) {
                indiceEncontrado = i;
                break;
            }
        }

        if (indiceEncontrado != -1) {
            System.out.println("\nResultado: matrícula encontrada en índice " + indiceEncontrado +
                    " (0-based), comparación " + comparaciones + ".");
        } else {
            System.out.println("\nResultado: matrícula NO encontrada tras " + comparaciones + " comparaciones.");
        }
    }

    // Explicación de búsqueda secuencial
    private static void explicarBusquedaSecuencial() {
        System.out.println("\n--- Qué es búsqueda secuencial ---");
        System.out.println("La búsqueda secuencial (linear search) recorre el arreglo elemento por elemento");
        System.out.println("comparando con la clave buscada hasta encontrarla o terminar el arreglo.\n");
        System.out.println("Complejidad temporal:");
        System.out.println(" - Mejor caso: O(1) (si el elemento está en la primera posición)");
        System.out.println(" - Peor y promedio: O(n) (si el elemento está al final o no está)");
        System.out.println("Espacio adicional: O(1) (sin estructuras auxiliares importantes)");
    }

}