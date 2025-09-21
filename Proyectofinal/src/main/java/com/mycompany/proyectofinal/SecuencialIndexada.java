package com.mycompany.proyectofinal;
import java.util.*;

/** Lógica de organización secuencial indexada. */
public class SecuencialIndexada {

    /** Nodo del índice, guarda la clave máxima de cada bloque y su posición de inicio. */
    static class NodoIndice {
        int claveMax;
        int posicion;

        public NodoIndice(int claveMax, int posicion) {
            this.claveMax = claveMax;
            this.posicion = posicion;
        }
    }

    private final List<Registro> datos;
    List<NodoIndice> indice;
    private final int tamanoBloque;

    public SecuencialIndexada(List<Registro> datos, int tamanoBloque) {
        this.datos = datos;
        this.tamanoBloque = tamanoBloque;
        construirIndice();
    }

    /** Construye la tabla de índice a partir de la lista de datos. */
    private void construirIndice() {
        indice = new ArrayList<>();
        int n = datos.size();
        for (int i = 0; i < n; i += tamanoBloque) {
            int finBloque = Math.min(i + tamanoBloque - 1, n - 1);
            int claveMaxBloque = datos.get(finBloque).clave;
            indice.add(new NodoIndice(claveMaxBloque, i));
        }
    }

    /** Busca un registro por su clave usando el índice. */
    public Registro buscar(int clave) {
        int bloque = -1;
        for (int i = 0; i < indice.size(); i++) {
            if (clave <= indice.get(i).claveMax) {
                bloque = i;
                break;
            }
        }
        if (bloque == -1) return null;

        int inicio = indice.get(bloque).posicion;
        int fin = Math.min(inicio + tamanoBloque - 1, datos.size() - 1);
        for (int i = inicio; i <= fin; i++) {
            if (datos.get(i).clave == clave) return datos.get(i);
        }
        return null;
    }
}
