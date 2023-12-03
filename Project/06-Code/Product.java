import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Product {
    private int id;
    private String name;
    private float cost;

    public static Date dateCurrent = new Date();
    public static SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("WELCOME ENTER YOUR PRODUCT");
        addProduct();
    }

    public static void date() {
        System.out.println("PRODUCT ENTRY DATE: " + date.format(dateCurrent));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateCurrent);
        calendar.add(Calendar.MONTH, 3);
        Date dueDate = calendar.getTime();

        System.out.println("PRODUCT DUE DATE: " + date.format(dueDate));
    }

    public static Product addProduct() {
        Product product = null;
        try {
            Scanner enter = new Scanner(System.in);
            enter.useLocale(Locale.US);
            int id;
            String name;
            float cost;

            System.out.println("Enter the id: ");
            id = enter.nextInt();
            enter.nextLine();

            System.out.println("Enter the name: ");
            name = enter.nextLine();

            System.out.println("Enter the cost: ");
            String costInput = enter.nextLine();
            cost = NumberFormat.getInstance().parse(costInput).floatValue();

            product = new Product(id, name, cost);
            System.out.println("Product-->" + product);
        } catch (ParseException ex) {
            System.out.println("There was a mistake...");
        }
        return product;
    }

    public Product(int id, String name, float cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
