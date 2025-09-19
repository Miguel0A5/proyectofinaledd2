package com.mycompany.proyectofinal;

// Clase que representa a un Alumno dentro del sistema de búsqueda secuencial
public class AlumnosSec {
    private String matricula;
    private String nombre;
    private double promedio;

    // Constructor para inicializar un alumno
    public AlumnosSec(String matricula, String nombre, double promedio) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    // Métodos getters
    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPromedio() {
        return promedio;
    }

    // Representación en texto de un alumno (para mostrar en listas)
    @Override
    public String toString() {
        return "Matrícula: " + matricula + " | Nombre: " + nombre + " | Promedio: " + promedio;
    }
}
