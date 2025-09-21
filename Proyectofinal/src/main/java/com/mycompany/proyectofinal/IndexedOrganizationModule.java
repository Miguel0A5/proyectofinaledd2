package com.mycompany.proyectofinal;

import java.io.IOException;
import java.util.*;

/**
 * Clase “puente” para que la Organización Indexada se ejecute
 * como un módulo más del menú principal.
 */
public class IndexedOrganizationModule {

    public void ejecutar() {
        try {
            IndexedOrganization.loadIndex();
            Scanner sc = new Scanner(System.in);
            boolean continuar = true;

            while (continuar) {
                System.out.println("\n=== Organización Indexada ===");
                System.out.println("1) Agregar estudiante");
                System.out.println("2) Buscar por ID");
                System.out.println("3) Actualizar por ID");
                System.out.println("4) Eliminar por ID");
                System.out.println("5) Listar todos");
                System.out.println("6) Salir de este módulo");
                System.out.print("Opción: ");
                String opt = sc.nextLine().trim();

                switch (opt) {
                    case "1": IndexedOrganization.addStudent(sc); break;
                    case "2": IndexedOrganization.searchStudent(sc); break;
                    case "3": IndexedOrganization.updateStudent(sc); break;
                    case "4": IndexedOrganization.deleteStudent(sc); break;
                    case "5": IndexedOrganization.listAll(); break;
                    case "6":
                        IndexedOrganization.saveIndex();
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}
