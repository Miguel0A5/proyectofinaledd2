package com.mycompany.proyectofinal;
import java.util.Arrays;   // Importamos la librería Arrays para poder ordenar listas fácilmente
import java.util.Scanner;  // Importamos Scanner para que el usuario pueda escribir en la consola

//  Esta clase se llama "BusquedaBinaria" y dentro de ella tendremos TODO el programa.
public class BusquedaBinaria {

    //  El programa siempre comienza por el método "main".
    // Aquí ponemos el menú principal con las opciones.
    public void ejecutar(){
        Scanner scanner = new Scanner(System.in);  // Creamos un "escáner" para leer lo que el usuario escriba en consola.
        int opcion;  // Variable para guardar qué opción elige el usuario.

        //  El menú se repetirá hasta que el usuario elija salir (opción 3).
        do {
            //  Mostramos el menú principal
            System.out.println("===================================");
            System.out.println("        MÉTODO BÚSQUEDA BINARIA    ");
            System.out.println("===================================");
            System.out.println("1. Iniciar Tutorial");    // Opción donde se le explica paso a paso
            System.out.println("2. Iniciar sin apoyo");   // Opción donde ya no hay explicaciones
            System.out.println("3. Salir");               // Opción para salir del programa
            System.out.print("Elige una opción: ");

            //  Validación: si el usuario no escribe un número, le avisamos.
            while (!scanner.hasNextInt()) {
                System.out.println("⚠️ Entrada inválida. Ingresa un número (1-3).");
                scanner.next();  // Esto limpia lo que el usuario escribió mal.
                System.out.print("Elige una opción: ");
            }
            opcion = scanner.nextInt();  // Guardamos la opción que eligió.

            // 🚦 Dependiendo de la opción, hacemos algo diferente.
            switch (opcion) {
                case 1:
                    iniciarTutorial(scanner);  // Iniciamos el modo tutorial
                    break;
                case 2:
                    iniciarSinApoyo(scanner); // Iniciamos el modo sin ayuda
                    break;
                case 3:
                    System.out.println("Saliendo del programa... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (opcion != 3);  // Mientras no sea "3", el menú se sigue mostrando.
    }

    // ============================
    //  MODO TUTORIAL (explicado como para niños)
    // ============================
    public static void iniciarTutorial(Scanner scanner) {
        System.out.println("\n=== INICIANDO TUTORIAL ===");
        System.out.println("\"Hola! Te voy a enseñar cómo funciona la búsqueda binaria.\"");
        System.out.println("\"La búsqueda binaria sirve para encontrar un número dentro de una lista\"");
        System.out.println("\"pero la lista tiene que estar ordenada de menor a mayor.\"");

        // PASO 1: Pedimos al niño que diga cuántos números quiere en la lista.
        System.out.println("\n\"PASO 1: Vamos a crear la lista de números.\"");
        System.out.println("\"Dime cuántos números quieres poner en tu lista.\"");
        int n = leerEntero(scanner);  // Aquí validamos que solo escriba números.

        // Creamos un arreglo (lista) con el tamaño que dijo el usuario.
        int[] arreglo = new int[n];

        // PASO 2: Pedimos los números de la lista.
        System.out.println("\n\"PASO 2: Ahora dime los números, uno por uno.\"");
        for (int i = 0; i < n; i++) {
            System.out.print("\"Número en la posición " + (i + 1) + ": \" ");
            arreglo[i] = leerEntero(scanner);  // Guardamos cada número.
        }

        // PASO 3: Ordenamos la lista antes de buscar.
        System.out.println("\n\"Muy bien, ya tenemos la lista, pero antes de buscar debemos ordenarla.\"");
        System.out.println("\"La ordenaré de menor a mayor para que la búsqueda binaria pueda funcionar.\"");
        Arrays.sort(arreglo);  // Ordenamos la lista.
        System.out.println("Lista ordenada: " + Arrays.toString(arreglo));

        // PASO 4: Pedimos qué número quiere buscar.
        System.out.println("\n\"PASO 3: Ahora dime el número que quieres buscar.\"");
        int numero = leerEntero(scanner);

        // PASO 5: Explicamos cómo funciona la búsqueda binaria.
        System.out.println("\n\"PASO 4: Vamos a empezar la búsqueda binaria.\"");
        System.out.println("\"Yo voy a dividir la lista a la mitad y revisar si el número que quieres\"");
        System.out.println("\"está en el centro, o si está a la izquierda, o si está a la derecha.\"");

        // Hacemos la búsqueda binaria con explicaciones paso a paso.
        int resultado = busquedaBinariaExplicada(arreglo, numero);

        // Dependiendo de si se encuentra o no, damos la respuesta final.
        if (resultado == -1) {
            System.out.println("\"Lo siento, el número " + numero + " no está en la lista.\"");
        } else {
            System.out.println("\"¡Sí lo encontré! El número " + numero + " está en la posición " + resultado + ".\"");
        }

        System.out.println("\n=== FIN DEL TUTORIAL ===\n");
    }

    // ============================
    //  MODO SIN APOYO (versión rápida, sin explicaciones)
    // ============================
    public static void iniciarSinApoyo(Scanner scanner) {
        System.out.println("\n=== MODO SIN APOYO ===");
        System.out.print("Ingresa el tamaño del arreglo: ");
        int n = leerEntero(scanner);

        int[] arreglo = new int[n];  // Creamos la lista.

        System.out.println("Ingresa los números:");
        for (int i = 0; i < n; i++) {
            arreglo[i] = leerEntero(scanner);  // Guardamos cada número.
        }

        Arrays.sort(arreglo);  // Ordenamos la lista antes de buscar.
        System.out.println("Arreglo ordenado: " + Arrays.toString(arreglo));

        System.out.print("Número a buscar: ");
        int numero = leerEntero(scanner);  // Número que se quiere buscar.

        int resultado = busquedaBinaria(arreglo, numero);  // Usamos la búsqueda binaria normal.

        if (resultado == -1) {
            System.out.println("Número NO encontrado.");
        } else {
            System.out.println("Número encontrado en la posición: " + resultado);
        }
    }

    // ============================
    //  BÚSQUEDA BINARIA NORMAL
    // ============================
    public static int busquedaBinaria(int[] arreglo, int clave) {
        int inicio = 0;               // Empezamos desde la primera posición (0)
        int fin = arreglo.length - 1; // Terminamos en la última posición (n-1)

        // Mientras todavía tengamos números que revisar...
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;  // Calculamos el punto medio.

            if (arreglo[medio] == clave) {   // ¿El número del medio es el que buscamos?
                return medio;  // Lo encontramos 
            } else if (arreglo[medio] < clave) {
                inicio = medio + 1;  // Si es menor, buscamos en la derecha.
            } else {
                fin = medio - 1;     // Si es mayor, buscamos en la izquierda.
            }
        }
        return -1;  // Si salimos del bucle, el número no estaba en la lista.
    }

    // ============================
    //  BÚSQUEDA BINARIA EXPLICADA (versión con narración)
    // ============================
    public static int busquedaBinariaExplicada(int[] arreglo, int clave) {
        int inicio = 0;
        int fin = arreglo.length - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;  // Calculamos la mitad.
            System.out.println("\"Estoy revisando el número en la posición " + medio + " que es " + arreglo[medio] + ".\"");

            if (arreglo[medio] == clave) {
                System.out.println("\"¡Sí! Encontré el número en la posición " + medio + ".\"");
                return medio;  // Número encontrado.
            } else if (arreglo[medio] < clave) {
                System.out.println("\"El número que busco es mayor que " + arreglo[medio] + ", así que descarto la izquierda.\"");
                inicio = medio + 1;  // Movemos el inicio hacia la derecha.
            } else {
                System.out.println("\"El número que busco es menor que " + arreglo[medio] + ", así que descarto la derecha.\"");
                fin = medio - 1;  // Movemos el fin hacia la izquierda.
            }
        }
        return -1;  // Si no lo encontramos, devolvemos -1.
    }

    // ============================
    //  MÉTODO AUXILIAR PARA VALIDAR NÚMEROS
    // ============================
    public static int leerEntero(Scanner scanner) {
        // Validamos que lo que el usuario escriba sea realmente un número.
        while (!scanner.hasNextInt()) {
            System.out.println("\"Ups, eso no es un número. Por favor escribe un número entero.\"");
            scanner.next();  // Limpiamos la entrada incorrecta.
        }
        return scanner.nextInt();  // Ahora sí devolvemos el número.
    }
}
