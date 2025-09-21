package com.mycompany.proyectofinal;

import java.util.*;

public class MenuPrincipal {

    public void ejecutar() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ Principal ---");
            System.out.println("1.- Busqueda Secuencial");
            System.out.println("2.- Búsqueda Binaria");
            System.out.println("3.- Búsqueda de Árbol");
            System.out.println("4.- Organización secuencial");
            System.out.println("No se sabe a quien le toco organizacion ram");
            System.out.println("6.- Organización secuencial indexada");
            System.out.println("7.- Organización indexada");
            System.out.println("8.- Aleatorio indexado");
            System.out.println("9.- Salir");
            // Aqui pongan sus opciones
            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {

                    /*
                     * Para inciar su porgrama aqui, escriban el nombre de su archivo, sesuigo de su
                     * nombre
                     * y al final le pondran new seguido dle nombre de su archivo y ();, despues
                     * ponen el
                     * nombre del objeto . y el nombre de su método que ejecute todo
                     */
                    case 1:
                        BusquedaAlumnos BusquedaSecuencial = new BusquedaAlumnos(); //Miguel Trees y Christian
                        BusquedaSecuencial.ejecutar();
                        break;

                    case 2:
                        BusquedaBinaria binaria = new BusquedaBinaria(); //Arturo
                        binaria.ejecutar();                        
                        break;

                    case 3:
                        ArbolBinarioModule arbolModule = new ArbolBinarioModule(); //Kevin
                        arbolModule.ejecutar();
                        break;

                    case 4:
                        MiguelA5Gomez miguelA5 = new MiguelA5Gomez(); // Organización secuencial
                        miguelA5.ejecutar();
                        break;

                    case 5:
                        //Murio por la paz
                        break;

                    case 6:
                        SecuencialIndexadaModule secIndexada = new SecuencialIndexadaModule(); //Eduardo
                        secIndexada.ejecutar();
                        break;

                    case 7:
                        IndexedOrganizationModule indexedModule = new IndexedOrganizationModule(); //Israel Otamendi
                        indexedModule.ejecutar();
                        break;
                        
                    case 8:
                        AleaIndx aleaIndx = new AleaIndx(); //Diego
                        aleaIndx.ejecutar();
                        break;

                    default:
                        System.out.println("Valor no válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, intente de nuevo.");
            }
        } while (opcion != 9);
    }
}
