package com.mycompany.proyectofinal;

/*
 Clase 
 Representa a un alumno con nombre y promedio.
 Es un simple objeto de datos (modelo) para almacenar y recuperar la información.*/
public class Alumno {
    private String nombre;
    private double promedio;

    // Constructor que recibe nombre y promedio
    public Alumno(String nombre, double promedio) {
        this.nombre = nombre;
        this.promedio = promedio;
    }

    // Devuelve el nombre del alumno
    public String getNombre() {
        return nombre;
    }

    // Devuelve el promedio del alumno
    public double getPromedio() {
        return promedio;
    }

    // Determina si el alumno está aprobado (promedio >= 6)
    public boolean estaAprobado() {
        return promedio >= 6.0;
    }

    // Convierte el alumno en una cadena "nombre,promedio" para guardarlo en el archivo
    @Override
    public String toString() {
        return nombre + "," + promedio;
    }
}
