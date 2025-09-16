package com.mycompany.proyectofinal;
import java.io.*;
import java.util.*;

/**
 * Se encarga de la ORGANIZACIÓN SECUENCIAL de los datos en el archivo de texto.
 * - Guarda cada alumno uno tras otro (sin sobrescribir).
 * - Lee los registros en el mismo orden en que fueron guardados.
 */
public class ArchivoSecuencial {
    // Nombre del archivo donde se guardarán los datos
    private final String archivo = "alumnos.txt";

    /**
     * Guarda un alumno en el archivo.
     * Se abre en modo "append" (añadir) para que los registros queden uno tras otro.
     */
    public void guardarAlumno(Alumno alumno) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(alumno.toString()); // escribe en el formato "nombre,promedio"
            bw.newLine(); // pasa a la siguiente línea (siguiente registro)
        } catch (IOException e) {
            System.out.println("Error al guardar alumno: " + e.getMessage());
        }
    }

    /**
     * Lee todos los alumnos del archivo, en el orden en que fueron guardados.
     * Devuelve una lista de objetos Alumno.
     */
    public List<Alumno> leerAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            // Lee línea por línea hasta llegar al final del archivo
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    String nombre = datos[0];
                    double promedio = Double.parseDouble(datos[1]);
                    lista.add(new Alumno(nombre, promedio));
                }
            }
        } catch (FileNotFoundException e) {
            // Si el archivo no existe, no es error: simplemente no hay registros aún
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
        return lista;
    }
}
