package com.mycompany.proyectofinal;

import java.util.Scanner;

public class BusquedaAlumnos {

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

    }











    
}
