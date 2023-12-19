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
    private int id;
    private Client client;
    private Date date;
    private ArrayList<Product> listOfProducts;
    private float totalValue;

    public SaleNote(int id, Client client, Date date, ArrayList<Product> listOfProducts, float totalValue) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.listOfProducts = listOfProducts;
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "SaleNote{" + "id=" + id + ", client=" + client + ", date=" + date + ", listOfProducts=" + listOfProducts + ", totalValue=" + totalValue + '}';
    }

    public static int getActualId(ArrayList<SaleNote> saleNotes) {
        int actualId = 0;
        for (SaleNote saleNoteCurrent : saleNotes) {
            actualId = saleNoteCurrent.getId();
        }
        return actualId + 1;
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

        // Selección del cliente
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
            // Selección del producto
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

            // Creación del producto en la venta
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
        products = jsonUtilProducts.readFile("Productdata.json", new TypeToken<ArrayList<Product>>() {
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
                    saleNotes.add(SaleNote.createSaleNote(clients, products, saleNotes));
                    jsonUtilSaleNotes.writeFile("salenotedata.json", saleNotes);
                    jsonUtilProducts.writeFile("Productdata.json", products);
                    break;
                case 2:
                    System.out.println("Listado");
                    for (SaleNote currentSaleNote : saleNotes) {
                        showSaleNote(currentSaleNote);
                    }
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
