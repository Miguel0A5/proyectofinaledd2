package com.mycompany.proyectofinal;

/** Nodo de un árbol binario. */
public class NodoArbol {
    int valor;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}
