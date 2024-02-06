
package ec.edu.espe.icecreamdeve.controller;

import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoCollection;
import ec.edu.espe.icecreamdeve.model.Client;
import ec.edu.espe.icecreamdeve.model.Invoice;
import ec.edu.espe.icecreamdeve.model.Product;
import ec.edu.espe.icecreamdeve.model.SaleNote;
import static ec.edu.espe.icecreamdeve.model.SaleNote.getScan;
import static ec.edu.espe.icecreamdeve.utils.Dates.validatedate;
import ec.edu.espe.icecreamdeve.utils.MDBManage;
import ec.edu.espe.icecreamdeve.utils.UseJson;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author mateo
 */
public class SaleNoteController extends MDBManage{
   private static Scanner scan = new Scanner(System.in);
    
      public static int getActualId(ArrayList<SaleNote> saleNotes) {
        int actualId = 0;
        for (SaleNote saleNoteCurrent : saleNotes) {
            actualId = saleNoteCurrent.getId();
        }
        return actualId + 1;
    }
      
    @Override
    public void register(Object object) {
        Class classType = SaleNote.class;
        String collection = "SaleNote";
        MongoCollection<SaleNote> invoiceDB = MDBManage.getFromCollection(collection, classType);
        invoiceDB.insertOne((SaleNote) object);
    }
    
    public ArrayList<SaleNote> findAllInvoices() {
        Class classType = SaleNote.class;
        String collection = "SaleNote";
        MongoCollection<SaleNote> invoiceDB = MDBManage.getFromCollection(collection, classType);
        ArrayList<SaleNote> saleNoteList = new ArrayList<>();
        invoiceDB.find().into(saleNoteList);
        return saleNoteList;
    }

    public static void showSaleNote(SaleNote saleNote) {
        System.out.println("\n////////Nota de venta//////////// ");
        System.out.println("Nota de Venta: " + saleNote.getId());
        System.out.println("Client: " + saleNote.client.getName() + "\tEmail:" + saleNote.client.getEmail());
        System.out.println("Cellphone:" + saleNote.client.getCellphoneNumber() + "\tDate: " + saleNote.date.toString());
        System.out.println("////Listado de Productos/////");
        System.out.println(saleNote.listOfProducts);
        System.out.println("Precio Final:" + saleNote.totalValue);
    }

     public static SaleNote createSaleNote(ArrayList<Client> clients, ArrayList<Product> products, ArrayList<SaleNote> saleNotes) {
        int idSaleNote = getActualId(saleNotes);

        
        System.out.println("1. Select a client (Type the id):");
        System.out.println("ID    Name            Email                         CellphoneNumber  isNorth  isMajority");
        System.out.println("----------------------------------------------------------------------------------------");
        for (Client client : clients) {
            System.out.println(client);
        }

        int clientIndex;
        while (true) {
            try {
                System.out.println("Enter the client index:");
                clientIndex = Integer.parseInt(getScan().nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        Client selectedClient = clients.get(clientIndex - 1);

        Date date = validatedate();
        ArrayList<Product> productsInSaleNote = new ArrayList<>();
        float totalValue = 0;
        int option = 0;

        do {
            
            System.out.println("2. Select a product (Type the id):");
            System.out.println("ID    Amount      Name             Cost");
            System.out.println("----------------------------------------------");
            System.out.println(products);

            int productIndex;
            while (true) {
                try {
                    System.out.println("Enter the product index:");
                    productIndex = Integer.parseInt(getScan().nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            Product selectedProduct = products.get(productIndex - 1);
            int idAux = selectedProduct.getId();
            int productsAvailable = selectedProduct.getAmount();
            int numberOfProducts;

            do {
                while (true) {
                    try {
                        System.out.println("Enter the number of products:");
                        numberOfProducts = Integer.parseInt(getScan().nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                }
                deduceProduct(products, idAux, numberOfProducts);
            } while (productsAvailable < numberOfProducts);

           
            Product productView = new Product(selectedProduct.getId(), selectedProduct.getAmount(), selectedProduct.getName(), selectedProduct.getCost());
            productView.setAmount(numberOfProducts);
            float costUnit = selectedProduct.getCost();
            boolean isNorth = selectedClient.isIsNorth();
            boolean isMajority = selectedClient.isIsMajority();

            // Aplicación de descuentos según las condiciones
            if (isNorth) {
                costUnit += 0.05f;
                productView.setCost(costUnit);
            }
            if (!isMajority) {
                costUnit += 0.20f;
                productView.setCost(costUnit);
            }

            productsInSaleNote.add(productView);
            totalValue += costUnit * numberOfProducts;

            System.out.println("Do you want to add more products? (1. Yes / 2. No)");

            while (true) {
                try {
                    option = Integer.parseInt(getScan().nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
        } while (option != 2);

        SaleNote saleNote = new SaleNote(idSaleNote, selectedClient, date, productsInSaleNote, totalValue);
        System.out.println("Sale Note created successfully!");
        showSaleNote(saleNote);

        
        MDBManage.registerSaleNote(saleNote, saleNotes);

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
        ArrayList<SaleNote> saleNotes = jsonUtilSaleNotes.readFile("salenotedata.json", new TypeToken<ArrayList<SaleNote>>() {}.getType());

        UseJson<Client> jsonUtilClients = new UseJson<>();
        clients = jsonUtilClients.readFile("clientdata.json", new TypeToken<ArrayList<Client>>() {}.getType());

        UseJson<Product> jsonUtilProducts = new UseJson<>();
        products = jsonUtilProducts.readFile("Productdata.json", new TypeToken<ArrayList<Product>>() {}.getType());

        int optionSaleNote;
        while (true) {
            System.out.println("///////////SaleNotes///////////");
            System.out.println("1. Create a sale note");
            System.out.println("2. Show sale notes");
            System.out.println("3. Delete sale notes");
            System.out.println("4. Return to the main menu");

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
                    saleNotes.add(SaleNoteController.createSaleNote(clients, products, saleNotes));
                    jsonUtilSaleNotes.writeFile("salenotedata.json", saleNotes);
                    jsonUtilProducts.writeFile("Productdata.json", products);
                    break;
                case 2:
                    System.out.println("Listado");
                    for (SaleNote currentSaleNote : saleNotes) {
                        SaleNoteController.showSaleNote(currentSaleNote);
                    }
                    break;
                case 3:
                    SaleNoteController.deleteSaleNote(saleNotes);
                    jsonUtilSaleNotes.writeFile("salenotedata.json", saleNotes);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static void deleteSaleNote(ArrayList<SaleNote> saleNotes) {
    System.out.println("List of Sale Notes:");
    for (SaleNote currentSaleNote : saleNotes) {
        SaleNoteController.showSaleNote(currentSaleNote);
    }

    int idToDelete;
    while (true) {
        try {
            System.out.println("Enter the Sale Note ID to delete:");
            idToDelete = Integer.parseInt(getScan().nextLine());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

   
    MDBManage.deleteSaleNote(idToDelete, saleNotes);
}

}
