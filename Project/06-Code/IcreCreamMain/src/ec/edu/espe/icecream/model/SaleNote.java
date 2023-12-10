package ec.edu.espe.icecream.model;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class SaleNote {

    private static Scanner scan = new Scanner(System.in);

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
        return "SaleNote{"
                + "client=" + client
                + ", date=" + date
                + ", numberOfProducts=" + numberOfProducts
                + ", totalValue=" + totalValue
                + '}';

    }

    public static SaleNote createSaleNote(ArrayList<Client> clients, ArrayList<Product> products) {
        System.out.println("//////////Create Sale Note/////////");
        System.out.println("Select a client:");
        System.out.println("Available clients: " + clients);
        int clientIndex = scan.nextInt();
        Client selectedClient = clients.get(clientIndex-1);
        System.out.println("Enter the date (in format yyyy-MM-dd):");
        String dateString = scan.next();
        Date date;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error parsing date. Please enter a valid date format.");
            return null;
        }
        System.out.println("Select a product:");
        System.out.println("Available products: " + products);
        int productIndex = scan.nextInt();
        Product selectedProduct = products.get(productIndex-1);
        int idAux=selectedProduct.getId();
        System.out.println("Enter the number of products:");
        int numberOfProducts = scan.nextInt();
        scan.nextLine();
        deduceProduct(products,idAux,numberOfProducts);
        float totalValue = selectedProduct.getCost() * numberOfProducts;

        SaleNote saleNote = new SaleNote(selectedClient, date, selectedProduct, totalValue);

        System.out.println("Sale Note created successfully!");

        return saleNote;
    }

    public static void deduceProduct(ArrayList<Product> products,int id,int numberOfProducts){
        for(Product currentProduct:products){
            int idProduct=currentProduct.getId();
            if(idProduct==id){
                int currentAmount=currentProduct.getAmount();
                int finalAmount=currentAmount-numberOfProducts;
                    if(finalAmount>=0){
                     currentProduct.setAmount(finalAmount);
                    }else{
                        System.out.println("No exiten productos suficientes");
                    }
            }
        }
    }
}
