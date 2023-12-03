
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author JuanGranda,Error 404,DCCO-ESPE
 */
public class IceCreamShop {

    public static Date dateCurrent = new Date();
    public static SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        int newProduct;
        System.out.println("WELCOME ENTER YOUR PRODUCT");
        Scanner enter = new Scanner(System.in);
        System.out.println("How many products: ");
        newProduct = enter.nextInt();
        while (newProduct > 0) {
            addProduct();
            newProduct--;
        }
        System.out.println(products);
    }

    public static Product addProduct() {
        Product product = null;
        try {
            Scanner enter = new Scanner(System.in);
            int id;
            String name;
            float cost;
            String newDate;
            String dueDate;

            System.out.println("Enter the id: ");
            id = enter.nextInt();
            enter.nextLine();

            System.out.println("Enter the name: ");
            name = enter.nextLine();

            System.out.println("Enter the cost: ");
            cost = Float.parseFloat(enter.nextLine());

            newDate = date.format(dateCurrent);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateCurrent);
            calendar.add(Calendar.MONTH, 3);
            Date dueDate1 = calendar.getTime();
            dueDate = date.format(dueDate1);
            product = new Product(id, name, cost, newDate, dueDate);
            products.add(product);
            System.out.println("Product-->" + product);
        } catch (Exception ex) {
            System.out.println("There was a mistake...");
        }
        return product;
    }

}
