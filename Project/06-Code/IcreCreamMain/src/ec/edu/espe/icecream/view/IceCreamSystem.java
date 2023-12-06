package ec.edu.espe.icecream.view;

import ec.edu.espe.model.icecream.Client;
import ec.edu.espe.model.icecream.GestionClient;
import java.util.Scanner;

/**
 *
 * @author Carlos Hernandez, Mateo Iza
 */
public class IceCreamSystem {
static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner scannerClient = new Scanner(System.in);

        // Crear un objeto GestionClient
        GestionClient gestionClient = new GestionClient();
        int option = 0;
        do{
            System.out.println("///////Ice Cream System/////////");
            System.out.println("1.Show Inventory");
            System.out.println("2.Add Invoice");
            System.out.println("3.Add Clients");
            System.out.println("4.Show clients");
            System.out.println("5.Generate a SaleNote");
            System.out.println("6.Show the business report");
            System.out.println("7. Exit");
            option=scan.nextInt();
            scan.nextLine();
            switch(option){
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                    System.out.println("Ingrese los datos del Cliente:");

        System.out.print("ID: ");
        int id = scannerClient.nextInt();
        scannerClient.nextLine(); // Consumir la nueva línea

        System.out.print("Name: ");
        String name = scannerClient.nextLine();

        System.out.print("Email: ");
        String email = scannerClient.nextLine();

        System.out.print("Número de teléfono: ");
        String cellphoneNumber = scannerClient.nextLine();

        System.out.print("¿Es del sur? (true/false): ");
        boolean ifsouth = scannerClient.nextBoolean();

        System.out.print("¿Es mayor de edad? (true/false): ");
        boolean ifmajority = scannerClient.nextBoolean();

        // Crear el objeto Cliente con los datos proporcionados
        Client client = new Client(id, name, email, cellphoneNumber, ifsouth, ifmajority);

        // Agregar el cliente a la lista de clientes en GestionClient
        gestionClient.agregarCliente(client);
                }
                case 4 -> {
                }
                case 5 -> {
                }
                case 6 -> {
                }
                case 7 -> {
                }
            }
            //Aqui deberiamos desarrollar una funcion que muestre el arhivo Json del inventario
            //Aqui debemos crear la funcion de crear notas de venta
            //Aui debemos mostrar la lista de clientes del json
            //Aqui creamos la funcion de crear una nota de venta o factura
            //Mostrar el reporte de ventas en funcion de los invoice y las notesale
                        
        }while(option!=7);
        
        
        
    }

}
