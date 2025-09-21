package com.mycompany.proyectofinal;
import java.util.*;

/**
 * Clase puente para que la búsqueda Secuencial Indexada
 * se ejecute como un módulo del menú principal.
 */
public class SecuencialIndexadaModule {

    public void ejecutar() {
        Scanner sc = new Scanner(System.in);

        // Datos de ejemplo ordenados por clave
        List<Registro> lista = new ArrayList<>();
        lista.add(new Registro(10, "datoA"));
        lista.add(new Registro(20, "datoB"));
        lista.add(new Registro(30, "datoC"));
        lista.add(new Registro(40, "datoD"));
        lista.add(new Registro(50, "datoE"));
        lista.add(new Registro(60, "datoF"));
        lista.add(new Registro(70, "datoG"));

        SecuencialIndexada si = new SecuencialIndexada(lista, 3);

        System.out.println("=== Búsqueda Secuencial Indexada ===");
        System.out.println("Índice generado:");
        for (SecuencialIndexada.NodoIndice ni : si.indice) {
            System.out.println("Bloque inicia en posición " + ni.posicion +
                    ", claveMax = " + ni.claveMax);
        }

        System.out.print("\nIngresa la clave a buscar: ");
        try {
            int claveBuscada = Integer.parseInt(sc.nextLine());
            Registro encontrado = si.buscar(claveBuscada);
            if (encontrado != null) {
                System.out.println("Encontrado: " + encontrado);
            } else {
                System.out.println("Clave " + claveBuscada + " no encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida.");
        }
    }
}
