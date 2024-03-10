package ec.edu.espe.icecreamdeve.controller;

import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoCollection;
import ec.edu.espe.icecreamdeve.model.Invoice;
import static ec.edu.espe.icecreamdeve.model.Invoice.getScan;
import ec.edu.espe.icecreamdeve.model.Product;
import static ec.edu.espe.icecreamdeve.utils.Dates.validatedate;
import ec.edu.espe.icecreamdeve.utils.MDBManage;
import ec.edu.espe.icecreamdeve.utils.UseJson;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
 */
public class InvoiceController extends MDBManage {

    @Override
    public void register(Object object) {
        Class classType = Invoice.class;
        String collection = "Invoices";
        MongoCollection<Invoice> invoiceDB = MDBManage.getFromCollection(collection, classType);
        invoiceDB.insertOne((Invoice) object);
    }

    public static void showInvoice(Invoice Invoice) {
        System.out.println("\n////////Factura//////////// ");
        System.out.println("Nota de Venta: " + Invoice.getId() + "\tDate:" + Invoice.getDateI());
        System.out.println("////Listado de Productos/////");
        System.out.println(Invoice.getBoxes());
        System.out.println("Precio Final:" + Invoice.getValue());
    }

    public int getActualIdInvoice(ArrayList<Invoice> invoices) {
        int actualId = 0;
        for (Invoice invoiceCurrent : invoices) {
            actualId = invoiceCurrent.getId();
        }
        return actualId + 1;
    }
    
    public ArrayList<Invoice> findAllInvoices() {
        Class classType = Invoice.class;
        String collection = "Invoices";
        MongoCollection<Invoice> invoiceDB = MDBManage.getFromCollection(collection, classType);
        ArrayList<Invoice> invoiceList = new ArrayList<>();
        invoiceDB.find().into(invoiceList);
        return invoiceList;
    }

    public void addAmount(ArrayList<Product> products, int id, int amount) {
        for (Product currentProduct : products) {
            int idProduct = currentProduct.getId();
            if (idProduct == id) {
                int actualAmount = currentProduct.getAmount();
                currentProduct.setAmount(actualAmount + amount);
            }
        }
    }
    
     public static Invoice addInvoice(ArrayList<Invoice> invoices, ArrayList<Product> products) {
        InvoiceController controller=new InvoiceController();
        int idInvoice = controller.getActualIdInvoice(invoices);
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

            controller.addAmount(products, idAux, numberOfProducts);
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
        showInvoice(invoice);
        return invoice;
     }
     
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
                    invoices.add(InvoiceController.addInvoice(invoices, products));
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
}
