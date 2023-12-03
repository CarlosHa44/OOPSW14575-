
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author JuanGranda,Error 404,DCCO-ESPE
 */
public class IceCreamShop {

    public static Date dateCurrent = new Date();
    public static SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        int newProduct;
        System.out.println("WELCOME ENTER YOUR PRODUCT");
        Scanner enter = new Scanner(System.in);
        System.out.println("How many products: ");
        newProduct = enter.nextInt();
        while (newProduct > 0) {
            addProduct();
            newProduct--;
        }
        System.out.println(products);
        
        // ingresar datos del cliente 
        Scanner scanner = new Scanner(System.in);

        // Crear un objeto GestionClient
        GestionClient gestionClient = new GestionClient();

        // Solicitar al usuario que ingrese los datos del cliente
        System.out.println("Ingrese los datos del Cliente:");

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Número de teléfono: ");
        String cellphoneNumber = scanner.nextLine();

        System.out.print("¿Es del sur? (true/false): ");
        boolean ifsouth = scanner.nextBoolean();

        System.out.print("¿Es mayor de edad? (true/false): ");
        boolean ifmajority = scanner.nextBoolean();

        // Crear el objeto Cliente con los datos proporcionados
        Client client = new Client(id, name, email, cellphoneNumber, ifsouth, ifmajority);

        // Agregar el cliente a la lista de clientes en GestionClient
        gestionClient.agregarCliente(client);
    }

    public static Product addProduct() {
        Product product = null;
        try {
            Scanner enter = new Scanner(System.in);
            int id;
            String name;
            float cost;
            String newDate;
            String dueDate;

            System.out.println("Enter the id: ");
            id = enter.nextInt();
            enter.nextLine();

            System.out.println("Enter the name: ");
            name = enter.nextLine();

            System.out.println("Enter the cost: ");
            cost = Float.parseFloat(enter.nextLine());

            newDate = date.format(dateCurrent);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateCurrent);
            calendar.add(Calendar.MONTH, 3);
            Date dueDate1 = calendar.getTime();
            dueDate = date.format(dueDate1);
            product = new Product(id, name, cost, newDate, dueDate);
            products.add(product);
            System.out.println("Product-->" + product);
        } catch (Exception ex) {
            System.out.println("There was a mistake...");
        }
        return product;
    }

}
