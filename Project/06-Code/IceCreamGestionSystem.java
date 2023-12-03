package ec.espe.edu.view;
import ec.espe.edu.model.Client;
import ec.espe.edu.model.GestionClient;
import java.util.Scanner;

/**
 *
 * @author mateo
 */
public class IceCreamGestionSystem {
       public static void main(String[] args) {
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
}