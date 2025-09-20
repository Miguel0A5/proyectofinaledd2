import java.util.*;

// Clase que representa un registro de datos
class Registro {
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

public class SecuencialIndexada {

    // Estructura del índice: para cada bloque se guarda la clave máxima del bloque
    // y la posición (índice) en la lista de datos donde inicia ese bloque.
    static class NodoIndice {
        int claveMax; // clave más alta del bloque
        int posicion; // posición de inicio del bloque en la lista de datos

        public NodoIndice(int claveMax, int posicion) {
            this.claveMax = claveMax;
            this.posicion = posicion;
        }
    }

    // Datos organizados secuencialmente, en bloques, más un índice
    List<Registro> datos; // lista de registros ordenados por clave
    List<NodoIndice> indice; // la tabla de índice

    int tamanoBloque; // cuantos registros hay por bloque

    public SecuencialIndexada(List<Registro> datos, int tamanoBloque) {
        // Suponer que los datos ya vienen ordenados por clave
        this.datos = datos;
        this.tamanoBloque = tamanoBloque;
        construirIndice();
    }

    // Construye la tabla de índices
    private void construirIndice() {
        indice = new ArrayList<>();

        int n = datos.size();
        for (int i = 0; i < n; i += tamanoBloque) {
            int finBloque = Math.min(i + tamanoBloque - 1, n - 1);
            int claveMaxBloque = datos.get(finBloque).clave;
            // posición de inicio del bloque = i
            indice.add(new NodoIndice(claveMaxBloque, i));
        }
    }

    // Búsqueda secuencial indexada: busca un registro dado su clave
    public Registro buscar(int clave) {
        // 1. Buscar en el índice para ver qué bloque puede contener la clave
        int bloque = -1;
        for (int i = 0; i < indice.size(); i++) {
            if (clave <= indice.get(i).claveMax) {
                bloque = i;
                break;
            }
        }
        if (bloque == -1) {
            // Si la clave es mayor que cualquier claveMax de los índices
            return null; // no encontrado
        }

        // 2. Saber los límites del bloque en los datos
        int inicio = indice.get(bloque).posicion;
        int fin = Math.min(inicio + tamanoBloque - 1, datos.size() - 1);

        // 3. Hacer búsqueda secuencial dentro del bloque
        for (int i = inicio; i <= fin; i++) {
            if (datos.get(i).clave == clave) {
                return datos.get(i); // encontrado
            }
        }

        // no encontrado
        return null;
    }

    // Ejemplo de uso
    public static void main(String[] args) {
        // construir lista de registros ordenados por clave
        List<Registro> lista = new ArrayList<>();
        lista.add(new Registro(10, "datoA"));
        lista.add(new Registro(20, "datoB"));
        lista.add(new Registro(30, "datoC"));
        lista.add(new Registro(40, "datoD"));
        lista.add(new Registro(50, "datoE"));
        lista.add(new Registro(60, "datoF"));
        lista.add(new Registro(70, "datoG"));

        // definir tamaño de bloque = cuántos registros por bloque en el índice
        SecuencialIndexada si = new SecuencialIndexada(lista, 3);

        // mostrar índice
        System.out.println("Índice:");
        for (NodoIndice ni : si.indice) {
            System.out.println("Bloque inicia en posición " + ni.posicion +
                    ", claveMax = " + ni.claveMax);
        }

        // buscar una clave
        int claveBuscada = 50;
        Registro encontrado = si.buscar(claveBuscada);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado);
        } else {
            System.out.println("Clave " + claveBuscada + " no encontrada.");
        }

        // buscar una que no existe
        claveBuscada = 55;
        encontrado = si.buscar(claveBuscada);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado);
        } else {
            System.out.println("Clave " + claveBuscada + " no encontrada.");
        }
    }
}
