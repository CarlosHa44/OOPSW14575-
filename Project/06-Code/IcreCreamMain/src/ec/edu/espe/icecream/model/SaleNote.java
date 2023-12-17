package ec.edu.espe.icecream.model;

import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import static ec.edu.espe.icecream.utils.Dates.validatedate;
import ec.edu.espe.icecream.utils.UseJson;

/**
 *
 * @author Carlos
 */
public class SaleNote {

    private static Scanner scan = new Scanner(System.in);

    private Client client;
    private Date date;
    private ArrayList<Product> listOfProducts;
    private float totalValue;

    public SaleNote(Client client, Date date, ArrayList<Product> listOfProducts, float totalValue) {
        this.client = client;
        this.date = date;
        this.listOfProducts = listOfProducts;
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "SaleNote{" + "client=" + client + ", date=" + date + ", listOfProducts=" + listOfProducts + ", totalValue=" + totalValue + '}';
    }

    public static SaleNote createSaleNote(ArrayList<Client> clients, ArrayList<Product> products) {
        System.out.println("1. Select a client (Type the id):");
        System.out.println("Available clients: " + clients);
        int clientIndex = scan.nextInt();
        Client selectedClient = clients.get(clientIndex - 1);

        Date date = validatedate();

        ArrayList<Product> productsInSaleNote = new ArrayList<>();
        float totalValue = 0;

        int option = 0;
        do {
            System.out.println("2. Select a product (Type the id):");
            System.out.println("Available products: " + products);
            int productIndex = scan.nextInt();
            Product selectedProduct = products.get(productIndex - 1);

            int idAux = selectedProduct.getId();

            int productsAvailable = selectedProduct.getAmount();
            int numberOfProducts;
            do {
                System.out.println("Enter the number of products:");
                numberOfProducts = scan.nextInt();
                scan.nextLine();
                deduceProduct(products, idAux, numberOfProducts);
            } while (productsAvailable < numberOfProducts);

            float costUnit = selectedProduct.getCost();
            boolean isNorth = selectedClient.isIsNorth();
            boolean isMajority = selectedClient.isIsMajority();
            if (isNorth == true) {
                costUnit += 0.05f;
            }
            if (isMajority == false) {
                costUnit += 0.20f;
            }

            productsInSaleNote.add(selectedProduct);
            totalValue += costUnit * numberOfProducts;

            System.out.println("Do you want to add more products? (1. Yes / 2. No)");
            option = scan.nextInt();
            scan.nextLine();
        } while (option != 2);

        SaleNote saleNote = new SaleNote(selectedClient, date, productsInSaleNote, totalValue);
        System.out.println("Sale Note created successfully!");
        return saleNote;
    }

    public static void deduceProduct(ArrayList<Product> products, int id, int numberOfProducts) {
        for (Product currentProduct : products) {
            int idProduct = currentProduct.getId();
            if (idProduct == id) {
                int currentAmount = currentProduct.getAmount();
                int finalAmount = currentAmount - numberOfProducts;
                if (finalAmount >= 0) {
                    currentProduct.setAmount(finalAmount);
                } else {
                    System.out.println("Not enough products available");
                }
            }
        }
    }

    public static void menuSaleNote(ArrayList<Product> products, ArrayList<Client> clients) {
        UseJson<SaleNote> jsonUtilSaleNotes = new UseJson<>();
        ArrayList<SaleNote> saleNotes = jsonUtilSaleNotes.readFile("salenotedata.json", new TypeToken<ArrayList<SaleNote>>() {
        }.getType());

        UseJson<Client> jsonUtilClients = new UseJson<>();
        clients = jsonUtilClients.readFile("clientdata.json", new TypeToken<ArrayList<Client>>() {
        }.getType());

        UseJson<Product> jsonUtilProducts = new UseJson<>();
        products = jsonUtilProducts.readFile("productdata.json", new TypeToken<ArrayList<Product>>() {
        }.getType());

        int optionSaleNote;
        while (true) {
            System.out.println("///////////SaleNotes///////////");
            System.out.println("1. Create a sale note");
            System.out.println("2. Show sale notes");
            System.out.println("3. Return to the main menu");

            if (getScan().hasNextInt()) {
                optionSaleNote = getScan().nextInt();
                getScan().nextLine();  
            } else {
                System.out.println("Invalid input. Please enter a number.");
                getScan().nextLine();  
                continue;  
            }

            switch (optionSaleNote) {
                case 1:
                    saleNotes.add(SaleNote.createSaleNote(clients, products));
                    jsonUtilSaleNotes.writeFile("salenotedata.json", saleNotes);
                    break;
                case 2:
                    System.out.println("Sale Notes" + saleNotes);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * @return the scan
     */
    public static Scanner getScan() {
        return scan;
    }

    /**
     * @param aScan the scan to set
     */
    public static void setScan(Scanner aScan) {
        scan = aScan;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the listOfProducts
     */
    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    /**
     * @param listOfProducts the listOfProducts to set
     */
    public void setListOfProducts(ArrayList<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    /**
     * @return the totalValue
     */
    public float getTotalValue() {
        return totalValue;
    }

    /**
     * @param totalValue the totalValue to set
     */
    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }
}
