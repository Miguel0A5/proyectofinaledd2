package com.mycompany.proyectofinal;

import java.util.InputMismatchException;
import java.util.Scanner;
public class BusquedaAlumnos {
    // Arreglo para almacenar alumnos (capacidad máxima 100)
    private static AlumnosSec[] alumnos = new AlumnosSec[100];
    private static int total = 0; // número actual de alumnos registrados

    public static void main(String[] args) {
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

}