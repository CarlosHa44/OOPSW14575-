package ec.edu.espe.icecream.model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class Inventory {

    static Scanner scan = new Scanner(System.in);
    private int id;

    public Inventory(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Inventory{" + "id=" + getId() + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Product addProduct(ArrayList<Product> products) {
        int idaux=getActualId(products);
        System.out.println("Ingresa la cantidad de productos que dese agregar");
        int amountaux = scan.nextInt();
        scan.nextLine();
        System.out.println("Ingrese el nombre del producto");
        String nameaux = scan.nextLine();

        System.out.println("Ingrese el costo del producto");
        float costaux = scan.nextFloat();
        return new Product(idaux, amountaux, nameaux, costaux);
    }


    public static void deleteProduct(ArrayList<Product> products) {
        System.out.println("Ingrese el id a eliminar");
        int idDelete = scan.nextInt();
        scan.nextLine();
        for (Product currentProduct : products) {
            int idProduct = currentProduct.getId();
            String nameProduct = currentProduct.getName();
            if (idProduct == idDelete) {
                System.out.println("Su producto es-->" + nameProduct);
                products.remove(currentProduct);
            }
        }
    }
    public static int getActualId(ArrayList<Product> products){
        int idProduct=0;
        for (Product currentProduct : products){
            idProduct = currentProduct.getId();
        }
        return idProduct+1;
    }
}
