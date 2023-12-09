
package ec.edu.espe.icecream.model;
import java.util.ArrayList;
import java.util.Date;
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
    public static Invoice addInvoice(ArrayList<Invoice> invoices,ArrayList<Product> products){
        Product productAux;
        System.out.println("Ingrese el dia");
        int day=scan.nextInt();
        scan.nextLine();
        System.out.println("Ingrese el mes");
        int month=scan.nextInt();
        scan.nextLine();
        System.out.println("Ingrese el año");
        int year=scan.nextInt();
        scan.nextLine();
        Date dateAux=new Date(year, month, day);
        System.out.println("Ingrese el Id del producto a agregar");
        int idAux=scan.nextInt();
        scan.nextLine();
        productAux=getProduct(products,idAux);
        int currentAmount=productAux.getAmount();
        addAmount(products, idAux, currentAmount);
        System.out.println("Ingrese el precio unitario mayorista");
        float unitCost=scan.nextFloat();
        scan.nextLine();
        float value=currentAmount*unitCost;
        return new Invoice(idAux, dateAux, value, productAux); 
    }
    
    public static void addAmount(ArrayList<Product> products,int id,int amount) {
        for (Product currentProduct : products) {
            int idProduct = currentProduct.getId();
                if (idProduct == id) {
                    int actualAmount=currentProduct.getAmount();
                    currentProduct.setAmount(actualAmount + amount);
                }
        }
    }
    public static Product getProduct(ArrayList<Product> products,int id){
        Product productAux;
        for (Product currentProduct : products) {
            int idProduct = currentProduct.getId();
                if (idProduct == id) {
                    String nameProduct = currentProduct.getName();
                    int actualAmount=currentProduct.getAmount();
                    System.out.println("Su producto es-->" + nameProduct);
                    System.out.println("Ingrese la cantidad que desea ingresar");
                    int amountProduct = scan.nextInt();
                    scan.nextLine();
                    productAux=currentProduct;
                    currentProduct.setAmount(actualAmount + amountProduct);
                    return productAux;
                }
        }
        System.out.println("Id no encontrado");
        return null;
    }
}
