package com.mycompany.heap;
import java.util.Random;
import java.util.Scanner;

public class AleaIndx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        
        // Pedir al usuario la cantidad de números
        System.out.print("Ingrese la cantidad de números que quiere ingresar: ");
        int n = sc.nextInt();
        int[] numeros = new int[n];
        
        // Ingresar los números
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el numero " + (i + 1) + ": ");
            numeros[i] = sc.nextInt();
        }
        System.out.println("\nLos numeros ingresados son:");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        
        // Mostrar los números en orden aleatorio (aleatorio indexado)
        System.out.println("\n\nNumeros en orden aleatorio:");
        boolean[] usados = new boolean[n]; // para no repetir índices
        for (int i = 0; i < n; i++) {
            int indice;
            do {
                indice = rand.nextInt(n); // generar índice aleatorio
            } while (usados[indice]);
            usados[indice] = true;
            System.out.print(numeros[indice] + " ");
        }
        sc.close();
    }
}