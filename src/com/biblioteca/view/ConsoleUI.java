package com.biblioteca.view;

import java.util.Scanner;

import com.biblioteca.manager.UserManager;

public class ConsoleUI {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("Menú Principal");
            System.out.println("1. Agregar nuevo usuario");
            System.out.println("2. Verificar credenciales de usuario");
            System.out.println("3. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre de usuario: ");
                    String username = scanner.nextLine();
                    System.out.print("Ingrese la contraseña: ");
                    String password = scanner.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("¿Es un usuario premium? (true/false): ");
                    boolean isPremium = scanner.nextBoolean();

                    boolean added = userManager.addUser(username, password, name, isPremium);
                    if (added) {
                        System.out.println("Usuario agregado con éxito.");
                    } else {
                        System.out.println("El usuario ya existe.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el nombre de usuario: ");
                    username = scanner.nextLine();
                    System.out.print("Ingrese la contraseña: ");
                    password = scanner.nextLine();

                    if (userManager.checkUser(username, password)) {
                        System.out.println(
                                "Credenciales válidas. Usuario activo: " + userManager.getActiveUser().getName());
                    } else {
                        System.out.println("Credenciales inválidas.");
                    }
                    break;

                case 3:
                    exit = true;
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                    break;
            }
        }

        scanner.close();
    }
}
