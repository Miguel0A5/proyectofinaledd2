package com.mycompany.proyectofinal;

import java.util.*;

/**
 * Organización Aleatoria Indexada.
 * Pide una lista de números, los muestra en orden original y luego
 * los imprime en un orden completamente aleatorio sin repetir.
 */
public class AleaIndx {

    /** Método principal para integrarse al menú */
    public void ejecutar() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // Pedir la cantidad de números
        System.out.print("Ingrese la cantidad de números que quiere ingresar: ");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Fin del módulo.");
            return;
        }

        int[] numeros = new int[n];

        // Ingresar los números
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el número " + (i + 1) + ": ");
            try {
                numeros[i] = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Número no válido, se guardará 0.");
                numeros[i] = 0;
            }
        }

        // Mostrar números en orden original
        System.out.println("\nLos números ingresados son:");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Mostrar los números en orden aleatorio (aleatorio indexado)
        System.out.println("\nNúmeros en orden aleatorio:");
        boolean[] usados = new boolean[n]; // para no repetir índices
        for (int i = 0; i < n; i++) {
            int indice;
            do {
                indice = rand.nextInt(n); // índice aleatorio
            } while (usados[indice]);
            usados[indice] = true;
            System.out.print(numeros[indice] + " ");
        }
        System.out.println();
    }
}
