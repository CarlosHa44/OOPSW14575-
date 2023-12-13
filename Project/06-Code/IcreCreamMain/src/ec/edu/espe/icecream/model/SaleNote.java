package ec.edu.espe.icecream.model;

import java.util.Date;
import com.google.gson.reflect.TypeToken;
import static ec.edu.espe.icecream.utils.Dates.validatedate;
import ec.edu.espe.icecream.utils.UseJson;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class SaleNote {

    private static final Scanner scan = new Scanner(System.in);

    private Client client;
    private Date date;
    private Product numberOfProducts;
    private float totalValue;

    public SaleNote(Client client, Date date, Product numberOfProducts, float totalValue) {
        this.client = client;
        this.date = date;
        this.numberOfProducts = numberOfProducts;
        this.totalValue = totalValue;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Product numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "\nclient=" + client + ", date=" + date + ", numberOfProducts=" + numberOfProducts + ", totalValue=" + totalValue;

    }

    public static SaleNote createSaleNote(ArrayList<Client> clients, ArrayList<Product> products) {
        System.out.println("1.Select a client(Type the id):");
        System.out.println("Available clients: " + clients);
        int clientIndex = scan.nextInt();
        Client selectedClient = clients.get(clientIndex - 1);
        Date date=validatedate();
        System.out.println("2.Select a product(Type the id):");
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

        float totalValue = costUnit * numberOfProducts;
        SaleNote saleNote = new SaleNote(selectedClient, date, selectedProduct, totalValue);
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
                    System.out.println("No exiten productos suficientes");
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
        int optionSaleNote = 0;
        do {
            System.out.println("///////////SaleNotes///////////");
            System.out.println("1.Crear una nota de venta");
            System.out.println("2.Mostrar las notas de venta");
            System.out.println("3.Regresar al menu principal");
            optionSaleNote = scan.nextInt();
            scan.nextLine();

            switch (optionSaleNote) {
                case 1:
                    saleNotes.add(SaleNote.createSaleNote(clients, products));
                    jsonUtilSaleNotes.writeFile("salenotedata.json", saleNotes);
                    break;
                case 2:
                    System.out.println("SaleNOTES" + saleNotes);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (optionSaleNote != 3);
    }
}
