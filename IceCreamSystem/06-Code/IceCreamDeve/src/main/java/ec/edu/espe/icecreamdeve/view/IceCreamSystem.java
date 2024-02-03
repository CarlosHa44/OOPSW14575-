package ec.edu.espe.icecreamdeve.view;
import ec.edu.espe.icecreamdeve.model.Client;
import ec.edu.espe.icecreamdeve.model.Invoice;
import ec.edu.espe.icecreamdeve.model.Product;
import ec.edu.espe.icecreamdeve.model.ReportOfBusiness;
import ec.edu.espe.icecreamdeve.model.SaleNote;
import ec.edu.espe.icecreamdeve.utils.Authenticator;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
 */
public class IceCreamSystem {

    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Invoice> invoices = new ArrayList<>();
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<ReportOfBusiness> reports = new ArrayList<>();

    // Autenticación del usuario
    if (Authenticator.authenticateUser(scan)) {
        int option;
        while (true) {
            showMainMenu();
            if (scan.hasNextInt()) {
                option = scan.nextInt();
                scan.nextLine(); 
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine(); 
                continue; 
            }

            switch (option) {
                case 1:
                    Product.menuProduct(products);
                    break;
                case 2:
                    Invoice.menuInvoice(invoices, products);
                    break;
                case 3:
                    Client.menuClient(clients);
                    break;
                case 4:
                    SaleNote.menuSaleNote(products, clients);
                    break;
                case 5:
                    ReportOfBusiness.menuReportBusinness(reports);
                    break;
                case 6:
                    System.out.println("Exiting the Ice Cream System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    } else {
        System.out.println("Authentication failed. Exiting the Ice Cream System. Goodbye!");
    }
}
    
    

    public static void showMainMenu() {
        System.out.println("|///////Ice Cream System/////////|");
        System.out.println("|1.Inventory                     |");
        System.out.println("|2.Invoice                       |");
        System.out.println("|3.Clients                       |");
        System.out.println("|4.SaleNote                      |");
        System.out.println("|5.Business report               |");
        System.out.println("|6. Exit                         |");
    }
}