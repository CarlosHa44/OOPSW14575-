package ec.edu.espe.icecreamdeve.controller;

import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoCollection;
import ec.edu.espe.icecreamdeve.model.Inventory;
import ec.edu.espe.icecreamdeve.model.Invoice;
import ec.edu.espe.icecreamdeve.model.Product;
import ec.edu.espe.icecreamdeve.utils.MDBManage;
import ec.edu.espe.icecreamdeve.utils.UseJson;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class ProductController extends MDBManage {
    static Scanner scan = new Scanner(System.in);
    
    @Override
    public void register(Object object) {
        Class classType = Product.class;
        String collection = "Products";
        MongoCollection<Product> productDB = MDBManage.getFromCollection(collection, classType);
        productDB.insertOne((Product) object);
    }
        
    public int getActualId(ArrayList<Product> products) {
        int idProduct = 0;
        for (Product currentProduct : products) {
            idProduct = currentProduct.getId();
        }
        return idProduct + 1;
    }

    public ArrayList<Product> findAllProducts() {
        Class classType = Product.class;
        String collection = "Products";
        MongoCollection<Product> productDB = MDBManage.getFromCollection(collection, classType);
        ArrayList<Product> productList = new ArrayList<>();
        productDB.find().into(productList);
        return productList;
    }

    
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
}
