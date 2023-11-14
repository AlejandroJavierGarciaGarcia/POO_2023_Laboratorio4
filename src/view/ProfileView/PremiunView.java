package view.ProfileView;

import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;

import Model.BasicObjects.Book;
import Model.BasicObjects.Magazine;
import Model.BasicObjects.Premiun;
import view.MainUI;
import view.ProfileView.IProfileView.IUserView;

public class PremiunView implements IUserView{

    private Premiun User;
    private MainUI mainUI = new MainUI();
    Scanner scanner = new Scanner(System.in);

    public PremiunView(Premiun User) {
        this.User = User;
    }

    @Override
    public void View() throws IOException, ParserConfigurationException {
        while(true){
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("Bienvenido a la vista de usuario premiun");
            System.err.println("Usuario: " + User.getName() + " Tipo: Premiun");
            System.out.println("1. Aplicar Cupon");
            System.out.println("2. Cambiar Contraseña");
            System.out.println("3. Entrar a modo prestamos");
            System.out.println("4. Entrar a modo entregas");
            System.out.println("5. Salir");

            int option = 0;

            while (option < 1 || option > 5){
                System.out.print("Ingrese una opcion: ");
                option = scanner.nextInt();
            }

            switch (option){
                case 1:
                    User.ApplyCupon();
                    break;
                case 2:
                    System.out.println("Ingrese su nueva contraseña: ");
                    String password = System.console().readLine();
                    User.setPassword(password);
                    break;
                case 3:
                    ModoPrestamo();
                    break;
                case 4:
                    ModoSeleccion();
                    break;
                case 5:
                    mainUI.View();
            }
        }
    }

    @Override
    public void ModoPrestamo() throws IOException, ParserConfigurationException {
        while(true){
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("1. Definir Dia de entrega");
            System.out.println("2. Definir Horario de entrega (AM/PM)");
            System.out.println("3. Imprimir listado de prestamos");
            System.out.println("4. Seleccionar Direccion de envio");
            System.out.println("5. Salir");

            int option = 0;
            while (option < 1 || option > 5){
                System.out.print("Ingrese una opcion: ");
                option = scanner.nextInt();
            }

            switch (option){
                case 1:
                    System.out.println("Ingrese el dia de entrega: ");
                    int DeAquiACuantosDias = System.in.read();
                    if (DeAquiACuantosDias > 50 && User.getCupon() == false){
                        System.out.println("No se puede entregar en mas de 7 dias");
                        break;
                    }
                    if (DeAquiACuantosDias > 75 && User.getCupon() == true){
                        System.out.println("No se puede entregar en mas de 14 dias");
                        break;
                    }
                    User.DeAquiACuantosDias= DeAquiACuantosDias;
                    break;
                case 2:
                    System.out.println("Ingrese el horario de entrega: ");
                    String horario = System.in.toString();
                    User.setHoraDeEntrega(horario);
                    break;
                case 3:
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
                    System.out.println("Ingrese la direccion de envio: ");
                    String direccion = System.console().readLine();
                    User.setDomicilio(direccion);
                    break;
                
                case 5:
                    View();
                    break;
            }
        }
    }

    @Override
    public void ModoSeleccion() throws IOException, ParserConfigurationException {
        while(true){
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("1. Agregar Libro");
            System.out.println("2. Agregar Revista");
            System.out.println("3. Vaciar Lista");
            System.out.println("4. Salir");

            int option = 0;
            while (option < 1 || option > 4){
                System.out.print("Ingrese una opcion: ");
                option = scanner.nextInt();
            }
            
            switch (option){
                case 1:
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
                    System.out.println("Ingrese el nombre de la revista: ");
                    name = System.console().readLine();
                    System.out.println("Ingrese el precio de la revista: ");
                    price = System.in.read();
                    System.err.println("Ingrese el autor de la revista: ");
                    author = System.console().readLine();
                    System.out.println("Ingrese Editora de la revista");
                    editor =System.console().readLine();
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
}