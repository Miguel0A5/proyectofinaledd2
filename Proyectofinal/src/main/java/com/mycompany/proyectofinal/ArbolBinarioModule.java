package com.mycompany.proyectofinal;

import java.util.Scanner;

/**
 * Módulo para ejecutar un árbol binario
 * dentro del menú principal del proyecto.
 */
public class ArbolBinarioModule {

    public void ejecutar() {
        Scanner sc = new Scanner(System.in);
        Arbol arbol = new Arbol();

        System.out.println("=== Árbol Binario ===");
        System.out.println("Ingresa números enteros para insertar en el árbol.");
        System.out.println("Escribe 'fin' para terminar la inserción.\n");

        // Insertar valores desde teclado
        while (true) {
            System.out.print("Número a insertar (o 'fin'): ");
            String entrada = sc.nextLine();
            if (entrada.equalsIgnoreCase("fin")) break;
            try {
                int valor = Integer.parseInt(entrada);
                arbol.insertar(valor);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
            }
        }

        // Recorrido inorden
        System.out.println("\nRecorrido Inorden del árbol:");
        arbol.recorridoInorden();

        // Búsqueda de valores
        System.out.print("\nIngresa un número para buscar: ");
        try {
            int buscarValor = Integer.parseInt(sc.nextLine());
            boolean encontrado = arbol.buscar(buscarValor);
            System.out.println("¿Se encontró el valor " + buscarValor + "? " + encontrado);
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Fin del módulo.");
        }
    }
}
