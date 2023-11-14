package view.ProfileView;

import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;

import Controller.LoginManager;
import Model.BasicObjects.Book;
import Model.BasicObjects.Magazine;
import Model.BasicObjects.Estandar;
import view.MainUI;
import view.ProfileView.IProfileView.IUserView;

public class EstandarView implements IUserView{

    private Estandar User;
    private MainUI mainUI = new MainUI();
    Scanner scanner = new Scanner(System.in);
    public EstandarView(Estandar User) {
        this.User = User;
    }

    @Override
    public void View() throws IOException, ParserConfigurationException {
        while(true){
            System.out.flush();
            System.out.println("Bienvenido a la vista de usuario Estandar");
            System.err.println("Usuario: " + User.getName() + " Tipo: Estandar");
            System.out.println("1. Cambiar Contraseña");
            System.out.println("2. Entrar a modo prestamos");
            System.out.println("3. Entrar a modo entregas");
            System.out.println("4. Modificar Tipo de Usuario");
            System.out.println("5. Salir");


            int option = scanner.nextInt();
            

            switch (option){
                case 1:
                    System.out.println("Ingrese su nueva contraseña: ");
                    String password = System.console().readLine();
                    User.setPassword(password);
                    break;
                case 2:
                    ModoPrestamo();
                    break;
                case 3:
                    ModoSeleccion();
                    break;
                case 4:
                    LoginManager.ChangeUser(User);
                    break;
                case 5:
                    mainUI.View();
            }
        } 
    }   

    @Override
    public void ModoPrestamo() throws IOException, ParserConfigurationException {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("1. Definir Dia de entrega");
        System.out.println("2. Definir Sucursal");
        System.out.println("3. Imprimir listado de prestamos");
        System.out.println("4. Despues de 12 horas o 24 horas");
        System.out.println("5. Salir");

        int option = 0;
        while (option < 1 || option > 5){
            System.out.print("Ingrese una opcion: ");
            option = scanner.nextInt();

        }

        switch (option){
            case 1:
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println("Ingrese el dia de entrega: ");
                int DeAquiACuantosDias = System.in.read();
                if (DeAquiACuantosDias > 30){
                    System.out.println("El dia de entrega no puede ser Mayor a 30 dias");
                }
                User.DeAquiACuantosDias= DeAquiACuantosDias;
                break;
            case 2:
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println("Ingrese la sucursal: ");
                String sucursal = System.console().readLine();
                User.setSucursal(sucursal);
                break;

            case 3:
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println(User.ShowList());
                // No se porque no me deja usar variables normales en el lambda
                final double[] totalPrice = {0.0};

                User.getBibliList().get("Books").forEach((book) -> {
                    totalPrice[0] += book.getPrice();
                });

                User.getBibliList().get("Magazines").forEach((magazine) -> {
                    totalPrice[0] += magazine.getPrice();
                });

                System.out.println("Total price: " + totalPrice[0]);

                System.out.println("Total price: " + totalPrice[0]);

                break;
            case 4:
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.err.println("1. 12 horas");
                System.err.println("2. 24 horas");
                boolean Hour12Later = false;
                while (option < 1 || option > 2){
                    System.out.print("Ingrese una opcion: ");
                    option = System.in.read();
                    option = Integer.parseInt(String.valueOf((char) option));
                }
                if (option == 1){
                    Hour12Later = true;
                }
                User.setHour12Later(Hour12Later);
                break;
            case 5:
                View();
                break;
        }
        
    }

    @Override
    public void ModoSeleccion() throws IOException, ParserConfigurationException {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("1. Agregar Libro");
        System.out.println("2. Agregar Revista");
        System.out.println("3. Vaciar Lista");
        System.out.println("4. Salir");

        int option = 0;
        while (option < 1 || option > 4){
            System.out.print("Ingrese una opcion: ");
            option = System.in.read();
            option = Integer.parseInt(String.valueOf((char) option));
        }
        
        switch (option){
            case 1:
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println("Ingrese el nombre del libro: ");
                String name = System.console().readLine();
                System.out.println("Ingrese el precio del libro: ");
                int price = System.in.read();
                System.err.println("Ingrese el autor del libro: ");
                String author = System.console().readLine();
                System.out.println("Ingrese Editora del libro");
                String editor = System.console().readLine();
                User.AddBook(new Book(name, editor, author, price));
                
                break;
            case 2:
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println("Ingrese el nombre de la revista: ");
                name = System.console().readLine();
                System.out.println("Ingrese el precio de la revista: ");
                price = System.in.read();
                System.err.println("Ingrese el autor de la revista: ");
                author = System.console().readLine();
                System.out.println("Ingrese Editora de la revista");
                editor = System.console().readLine();
                User.AddMagazine(new Magazine(name, editor, author, price));
                break;
            case 3:
                User.EmptyList();
                break;
            case 4:
                View();
                break;
        }
    }
}