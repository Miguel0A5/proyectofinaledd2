package com.mycompany.proyectofinal;
import java.util.*;

/**
 * Clase principal que presenta el menú y llama a los métodos del GestorAlumnos.
 * Representa el punto de entrada del programa.
 */
public class MiguelA5Gomez {
    public void ejecutar() {
        Scanner entrada = new Scanner(System.in);
        GestorAlumnos gestor = new GestorAlumnos();
        int opcion = 0;
        
        System.out.println("En programación, la organización secuencial es una forma de estructurar ");
        System.out.println("y almacenar los registros (datos) dentro de un archivo siguiendo un orden");
        System.out.println("consecutivo, es decir, uno tras otro, tal como fueron creados o según un");
        System.out.println("criterio de ordenamiento previamente definido (por ejemplo, orden alfabético,");
        System.out.println("numérico como en este ejemplo).");

        do {
            // Muestra las opciones disponibles
            System.out.println("\n--- MENÚ Principal ---");
            System.out.println("1.- Agregar alumno");
            System.out.println("2.- Mostrar todos");
            System.out.println("3.- Buscar alumno por nombre");
            System.out.println("4.- Mostrar alumnos por orden alfabético");
            System.out.println("5.- Mostrar alumnos por promedio (mayor a menor)");
            System.out.println("6.- Calcular promedio general");
            System.out.println("7.- Exportar reporte");
            System.out.println("8.- Salir");

            try {
                opcion = Integer.parseInt(entrada.nextLine());
                switch (opcion) {
                    case 1:
                        gestor.agregarAlumno();
                        break;
                    case 2:
                        gestor.mostrarTodos();
                        break;
                    case 3:
                        gestor.buscarPorNombre();
                        break;
                    case 4:
                        gestor.buscarPorOrdenAlfabetico();
                        break;
                    case 5:
                        gestor.buscarPorPromedio();
                        break;
                    case 6:
                        gestor.calcularPromedioGeneral();
                        break;
                    case 7:
                        gestor.exportarReporte();
                        break;
                    case 8:
                        System.out.println("Volviendo al menu principal...");
                        break;
                    default:
                        System.out.println("Valor no válido.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, intente de nuevo.");
            }
        } while (opcion != 8);
    }

}
