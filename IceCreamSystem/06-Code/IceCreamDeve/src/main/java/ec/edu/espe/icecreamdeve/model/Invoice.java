package ec.edu.espe.icecreamdeve.model;

import java.util.Date;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoCollection;
import static ec.edu.espe.icecreamdeve.utils.Dates.validatedate;
import ec.edu.espe.icecreamdeve.utils.MDBManage;
import ec.edu.espe.icecreamdeve.utils.UseJson;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
 */
public class Invoice extends MDBManage {

    private static Scanner scan = new Scanner(System.in);
    private int id;
    private Date dateI;
    private float value;
    private ArrayList<Product> boxes;

    public Invoice(int id, Date dateI, float value, ArrayList<Product> boxes) {
        this.id = id;
        this.dateI = dateI;
        this.value = value;
        this.boxes = boxes;
    }
    
    public Invoice(){}

    public static void menuInvoice(ArrayList<Invoice> invoices, ArrayList<Product> products) {
        UseJson<Invoice> jsonUtilInvoice = new UseJson<>();
        invoices = jsonUtilInvoice.readFile("Invoicedata.json", new TypeToken<ArrayList<Invoice>>() {
        }.getType());
        UseJson<Product> jsonUtilProducts = new UseJson<>();
        products = jsonUtilProducts.readFile("Productdata.json", new TypeToken<ArrayList<Product>>() {
        }.getType());

        int optionInvoice;
        while (true) {
            System.out.println("//////////Invoice/////////");
            System.out.println("1.Add a new invoice");
            System.out.println("2.Show an invoice");
            System.out.println("3.Return to the main menu");

            // Verificar si la entrada es un número
            if (getScan().hasNextInt()) {
                optionInvoice = getScan().nextInt();
                getScan().nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                getScan().nextLine();
                continue;
            }

            switch (optionInvoice) {
                case 1:
                    invoices.add(Invoice.addInvoice(invoices, products));
                    jsonUtilProducts.writeFile("Productdata.json", products);
                    jsonUtilInvoice.writeFile("Invoicedata.json", invoices);
                    break;
                case 2:
                    System.out.println("Listado");
                    for (Invoice currentInvoice : invoices) {
                        showInvoice(currentInvoice);
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateI() {
        return dateI;
    }

    public void setDateI(Date dateI) {
        this.dateI = dateI;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public ArrayList<Product> getBoxes() {
        return boxes;
    }

    public void setBoxes(ArrayList<Product> boxes) {
        this.boxes = boxes;
    }

    public static Scanner getScan() {
        return scan;
    }

    public static void setScan(Scanner aScan) {
        scan = aScan;
    }

    public static void showInvoice(Invoice Invoice) {
        System.out.println("\n////////Factura//////////// ");
        System.out.println("Nota de Venta: " + Invoice.getId() + "\tDate:" + Invoice.getDateI());
        System.out.println("////Listado de Productos/////");
        System.out.println(Invoice.boxes);
        System.out.println("Precio Final:" + Invoice.value);
    }

    public static void addAmount(ArrayList<Product> products, int id, int amount) {
        for (Product currentProduct : products) {
            int idProduct = currentProduct.getId();
            if (idProduct == id) {
                int actualAmount = currentProduct.getAmount();
                currentProduct.setAmount(actualAmount + amount);
            }
        }
    }

    public static int getActualId(ArrayList<Invoice> invoices) {
        int actualId = 0;
        for (Invoice invoiceCurrent : invoices) {
            actualId = invoiceCurrent.getId();
        }
        return actualId + 1;
    }
     
    @Override
    public void register(Object object) {
            Class classType=Invoice.class;
            String collection= "Invoices";
            MongoCollection<Invoice> invoiceDB=MDBManage.getFromCollection(collection, classType);
            invoiceDB.insertOne((Invoice) object);
    }

    public static Invoice addInvoice(ArrayList<Invoice> invoices, ArrayList<Product> products) {
        int idInvoice = getActualId(invoices);
        Date date = validatedate();
        ArrayList<Product> productsInvoice = new ArrayList<>();
        int option;
        float totalValue = 0;

        do {
            System.out.println("2. Select a product (Type the id):");
            System.out.println("ID    Amount      Name             Cost");
            System.out.println("----------------------------------------------");
            for (Product product : products) {
                System.out.println(product);
            }

            int productIndex;
            boolean inputvalidation;
            while (true) {
                try {
                    do {
                        System.out.println("Enter the product index:");
                        productIndex = Integer.parseInt(getScan().nextLine());
                        if (productIndex > products.size()) {
                            inputvalidation = false;
                            System.out.println("Please enter the product correct id");
                        } else {
                            inputvalidation = true;

                        }
                    } while (!inputvalidation);
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            Product selectedProduct = products.get(productIndex - 1);
            int idAux = selectedProduct.getId();
            int numberOfProducts;

            while (true) {
                try {
                    System.out.println("Enter the number of products:");
                    numberOfProducts = Integer.parseInt(getScan().nextLine());
                    if (numberOfProducts > 0 && numberOfProducts <= selectedProduct.getAmount()) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid number of products.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            addAmount(products, idAux, numberOfProducts);
            float unitCost;

            while (true) {
                try {
                    System.out.println("Enter the unit cost:");
                    unitCost = Float.parseFloat(getScan().nextLine());
                    if (unitCost > 0 && unitCost == selectedProduct.getCost()) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid cost of products.");

                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            Product productView = new Product(selectedProduct.getId(), numberOfProducts, selectedProduct.getName(), unitCost);
            productsInvoice.add(productView);
            totalValue += unitCost * numberOfProducts;

            System.out.println("Do you want to add more products? (1. Yes / 2. No)");

            while (true) {
                try {
                    option = Integer.parseInt(getScan().nextLine());
                    if (option >= 1 && option <=2 ){
                        break;
                    }else{
                        System.out.println("Please enter a valid number");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

        } while (option != 2);

        Invoice invoice = new Invoice(idInvoice, date, totalValue, productsInvoice);
        invoice.register(invoice);
        
        showInvoice(invoice);
        return invoice;
    }


}
