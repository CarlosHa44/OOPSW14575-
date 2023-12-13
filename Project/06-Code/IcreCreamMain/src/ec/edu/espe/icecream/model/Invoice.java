package ec.edu.espe.icecream.model;

import java.util.Date;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.icecream.utils.UseJson;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class Invoice {

    static Scanner scan = new Scanner(System.in);
    private int id;
    private Date dateI;
    private float value;
    private Product boxes;

    public Invoice(int id, Date dateI, float value, Product boxes) {
        this.id = id;
        this.dateI = dateI;
        this.value = value;
        this.boxes = boxes;
    }

    public static void menuInvoice(ArrayList<Invoice> invoices, ArrayList<Product> products) {
        UseJson<Invoice> jsonUtilInvoice = new UseJson<>();
        invoices = jsonUtilInvoice.readFile("invoicedata.json", new TypeToken<ArrayList<Invoice>>() {
        }.getType());
        UseJson<Product> jsonUtilProducts = new UseJson<>();
        products = jsonUtilProducts.readFile("productdata.json", new TypeToken<ArrayList<Product>>() {
        }.getType());
        int optionInvoice = 0;
        do {
            System.out.println("//////////Invoice/////////");
            System.out.println("1.Añadir una nueva factura");
            System.out.println("2.Mostrar una factura");
            System.out.println("3.Regresar al menu principal");
            optionInvoice = scan.nextInt();
            scan.nextLine();
            switch (optionInvoice) {
                case 1:
                    invoices.add(Invoice.addInvoice(invoices, products));
                    jsonUtilProducts.writeFile("productdata.json", products);
                    jsonUtilInvoice.writeFile("invoicedata.json", invoices);
                    break;
                case 2:
                    System.out.println("array" + invoices);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (optionInvoice != 3);
    }

    @Override
    public String toString() {
        return "\n\tid=" + getId() + "\n\t dateI=" + getDateI() + "\n\t value=" + getValue() + "\n\t boxes=" + getBoxes();
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

    public Product getBoxes() {
        return boxes;
    }

    public void setBoxes(Product boxes) {
        this.boxes = boxes;
    }

    public static Invoice addInvoice(ArrayList<Invoice> invoices, ArrayList<Product> products) {
        Product productAux;
        int idInvoice = getActualId(invoices);
        Date dateAux = new Date();
        System.out.println("Ingrese el Id del producto a agregar");
        int idAux = scan.nextInt();
        scan.nextLine();
        productAux = getProduct(products, idAux);
        int currentAmount = productAux.getAmount();
        addAmount(products, idAux, currentAmount);
        System.out.println("Ingrese el precio unitario mayorista");
        float unitCost = scan.nextFloat();
        scan.nextLine();
        float value = currentAmount * unitCost;
        return new Invoice(idInvoice, dateAux, value, productAux);
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

    public static Product getProduct(ArrayList<Product> products, int id) {
        Product productAux;
        for (Product currentProduct : products) {
            int idProduct = currentProduct.getId();
            if (idProduct == id) {
                String nameProduct = currentProduct.getName();
                int actualAmount = currentProduct.getAmount();
                System.out.println("Su producto es-->" + nameProduct);
                System.out.println("Ingrese la cantidad que desea ingresar");
                int amountProduct = scan.nextInt();
                scan.nextLine();
                productAux = currentProduct;
                currentProduct.setAmount(actualAmount + amountProduct);
                return productAux;
            }
        }
        System.out.println("Id no encontrado");
        return null;
    }

    public static int getActualId(ArrayList<Invoice> invoices) {
        int actualId = 0;
        for (Invoice invoiceCurrent : invoices) {
            actualId = invoiceCurrent.getId();
        }
        return actualId + 1;
    }
}
