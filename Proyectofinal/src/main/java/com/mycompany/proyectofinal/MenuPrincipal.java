package com.mycompany.proyectofinal;

import java.util.*;

public class MenuPrincipal {

    public void ejecutar() {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ Principal ---");
            System.out.println("1.- Organizacíon secuencial");
            System.out.println("2.- Busqueda secuencial");
            System.out.println("3.- Busqueda binaria");
            System.out.println("7.- Salir del programa");
            // Aqui pongan sus opciones
            try {
                opcion = Integer.parseInt(entrada.nextLine());
                switch (opcion) {

                    /*
                     * Para inciar su porgrama aqui, escriban el nombre de su archivo, sesuigo de su
                     * nombre
                     * y al final le pondran new seguido dle nombre de su archivo y ();, despues
                     * ponen el
                     * nombre del objeto . y el nombre de su método que ejecute todo
                     */
                    case 1:
                        MiguelA5Gomez miguel = new MiguelA5Gomez();
                        miguel.ejecutar();
                        break;

                    case 2:
                        BusquedaAlumnos busqueda = new BusquedaAlumnos();
                        busqueda.ejecutar();
                        break;

                    case 3:
                        BusquedaBinaria binaria = new BusquedaBinaria();
                        binaria.ejecutar(); 
                        break;

                    case 4:
                        break;

                    case 5:
                        break;

                    case 6:
                        break;

                    case 7:
                        break;

                    default:
                        System.out.println("Valor no válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, intente de nuevo.");
            }
        } while (opcion != 7);

        entrada.close();
    }
}
