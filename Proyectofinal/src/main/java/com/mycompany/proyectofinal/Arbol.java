// Clase Nodo para el árbol binario
class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;

    Nodo(int valor) {
        this.valor = valor;
        izquierdo = null;
        derecho = null;
    }
}

// Clase Arbol Binario con métodos
class Arbol {
    Nodo raiz;

    Arbol() {
        raiz = null;
    }

    // Método para insertar un valor en el árbol
    void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo nodo, int valor) {
        if (nodo == null) {
            nodo = new Nodo(valor);
            return nodo;
        }
        if (valor < nodo.valor) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertarRec(nodo.derecho, valor);
        }
        // Si el valor es igual, no hacemos nada (no duplicados)
        return nodo;
    }

    // Método para recorrer el árbol en inorden
    void recorridoInorden() {
        recorridoInordenRec(raiz);
        System.out.println();
    }

    private void recorridoInordenRec(Nodo nodo) {
        if (nodo != null) {
            recorridoInordenRec(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            recorridoInordenRec(nodo.derecho);
        }
    }

    // Método para buscar un valor en el árbol
    boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(Nodo nodo, int valor) {
        if (nodo == null) {
            return false;
        }
        if (valor == nodo.valor) {
            return true;
        }
        return valor < nodo.valor ? buscarRec(nodo.izquierdo, valor) : buscarRec(nodo.derecho, valor);
    }
}

// Clase principal para ejecutar el árbol
public class Main {
    public static void main(String[] args) {
        Arbol arbol = new Arbol();

        // Insertar valores
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);

        // Recorrido inorden
        System.out.println("Recorrido Inorden del árbol:");
        arbol.recorridoInorden();

        // Buscar valores
        int buscarValor = 40;
        System.out.println("¿Se encontró el valor " + buscarValor + "? " + arbol.buscar(buscarValor));

        buscarValor = 90;
        System.out.println("¿Se encontró el valor " + buscarValor + "? " + arbol.buscar(buscarValor));
    }
}