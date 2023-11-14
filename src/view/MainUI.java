package view;

import java.io.IOException;
import java.util.ArrayList;

import Controller.LoginManager;
import Controller.RegisterManager;
import Model.BasicObjects.User;
import Model.BasicObjects.Estandar;
import Model.BasicObjects.Premiun;
import Model.FileManagers.CsvManager;

import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
public class MainUI {

    CsvManager csvManager = new CsvManager();
    ArrayList<User> users = csvManager.LoadUsers();
    Scanner scanner = new Scanner(System.in);

    public void View() throws IOException, ParserConfigurationException{
        while (true){
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
            System.out.println("Bienvenido a la Biblioteca");
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");

            int option = 0;
            while (option != 3){
                option = 0;

                    System.out.print("Ingrese una opcion: ");
                    option = scanner.nextInt();

                switch (option){
                    case 1:
                    
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        System.out.println(option);
                        System.out.println("Ingrese su nombre de usuario: ");
                        String name = System.console().readLine();
                        System.out.println("Ingrese su contraseña: ");
                        String password = System.console().readLine();

                        for (User user : users){
                            if (user.getName().equals(name) && user.getPassword().equals(password)){
                                try {
                                    LoginManager.login(user);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else{
                                System.out.println("El nombre de usuario o la contraseña son incorrectos");
                            }
                        }
                        break;
                    case 2:

                        System.out.print("\033[H\033[2J");  
                        System.out.flush();  
                        System.out.println("Que Tipo de Usuario Desea Crear?");
                        System.out.println("1. Estandar");
                        System.out.println("2. Premiun");

                        int Opcion = 0;

                        while (Opcion < 1 || Opcion > 2){
                            System.out.print("Ingrese una opcion: ");
                            Opcion = scanner.nextInt();
                        }
                        System.out.print("\033[H\033[2J");
                        System.out.flush();  
                        System.out.println("Ingrese su nombre de usuario: ");
                        String names = System.console().readLine();
                        System.out.println("Ingrese su contraseña: ");
                        String passwords = System.console().readLine();

                        for (User user : users){
                            if (user.getName().equals(names)){
                                System.out.println("El nombre de usuario ya existe");
                                break;
                            }
                        }

                        switch (Opcion){
                            case 1:
                                Estandar estandar = new Estandar(names, passwords);
                                users.add(estandar);
                                csvManager.SaveUser(users);
                                System.out.println(users);
                                RegisterManager.RegisterEstandar(estandar);
                                break;
                            case 2:
                                Premiun premiun = new Premiun(names, passwords);
                                users.add(premiun);
                                csvManager.SaveUser(users);
                                System.out.println(users);
                                RegisterManager.RegisterPremiun(premiun);
                                break;
                        }
                        break;
                    
                    case 3:
                        System.out.print("Adios");
                        break;
                    default:
                        break;

                }
            }

        }
    }
}