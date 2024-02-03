package ec.edu.espe.icecreamdeve.model;

import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoCollection;
import ec.edu.espe.icecreamdeve.utils.MDBManage;
import ec.edu.espe.icecreamdeve.utils.UseJson;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
 */
public class Product extends MDBManage{

    private int id;
    private int amount;
    private String name;
    private float cost;

    public Product(int id, int amount, String name, float cost) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.cost = cost;
    }
    static Scanner scan = new Scanner(System.in);

    public static void menuProduct(ArrayList<Product> products) {
        UseJson<Product> jsonUtilProducts = new UseJson<>();
        products = jsonUtilProducts.readFile("Productdata.json", new TypeToken<ArrayList<Product>>() {
        }.getType());
        
        int optionInventory;
        while (true) {
            System.out.println("///////////Inventory////////");
            System.out.println("1.Add a new product");
            System.out.println("2.Show inventory products");
            System.out.println("3.Return to the main menu");

            if (scan.hasNextInt()) {
                optionInventory = scan.nextInt();
                scan.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
                continue;
            }

            switch (optionInventory) {
                case 1:
                    products.add(Inventory.addProduct(products));
                    jsonUtilProducts.writeFile("Productdata.json", products);

                    break;
                case 2:
                    System.out.println("ID    Amount      Name             Cost");
                    System.out.println("----------------------------------------------");
                    System.out.println(products);

                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    
    public Product(){}
    

    public void register(Product productToMongo){
        Class classType=Product.class;
        String collection= "Products";
        MongoCollection<Product> contactDB=MDBManage.getFromCollection(collection, classType);
        contactDB.insertOne(productToMongo);
    }
    
    @Override
    public String toString() {
        return "id=" + id + "\tamount=" + amount + "\tname=" + name + "\tcost=" + cost + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public void register(Object object) {
        Class classType=Product.class;
        String collection= "Products";
        MongoCollection<Product> contactDB=MDBManage.getFromCollection(collection, classType);
        contactDB.insertOne((Product) object);
    }

}
