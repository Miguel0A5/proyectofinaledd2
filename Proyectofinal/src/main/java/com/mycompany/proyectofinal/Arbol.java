package com.mycompany.proyectofinal;

/** Estructura de un árbol binario con operaciones básicas. */
public class Arbol {
    private NodoArbol raiz;

    public Arbol() {
        raiz = null;
    }

    /** Inserta un valor en el árbol. */
    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private NodoArbol insertarRec(NodoArbol nodo, int valor) {
        if (nodo == null) return new NodoArbol(valor);
        if (valor < nodo.valor) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertarRec(nodo.derecho, valor);
        }
        // Si es igual, no lo inserta para evitar duplicados
        return nodo;
    }

    /** Recorre el árbol en orden y muestra los valores. */
    public void recorridoInorden() {
        recorridoInordenRec(raiz);
        System.out.println();
    }

    private void recorridoInordenRec(NodoArbol nodo) {
        if (nodo != null) {
            recorridoInordenRec(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            recorridoInordenRec(nodo.derecho);
        }
    }

    /** Busca un valor en el árbol. */
    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(NodoArbol nodo, int valor) {
        if (nodo == null) return false;
        if (valor == nodo.valor) return true;
        return valor < nodo.valor
                ? buscarRec(nodo.izquierdo, valor)
                : buscarRec(nodo.derecho, valor);
    }
}
