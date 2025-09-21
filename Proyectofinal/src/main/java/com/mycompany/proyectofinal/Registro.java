package com.mycompany.proyectofinal;

/** Representa un registro de datos para búsqueda secuencial indexada. */
public class Registro {
    int clave;
    String datos;

    public Registro(int clave, String datos) {
        this.clave = clave;
        this.datos = datos;
    }

    @Override
    public String toString() {
        return "Registro{" + "clave=" + clave + ", datos='" + datos + '\'' + '}';
    }
}
