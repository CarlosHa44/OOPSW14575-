package ec.edu.espe.icecreamdeve.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
 */
public class SaleNote {

    private static Scanner scan = new Scanner(System.in);
    private int id;
    public Client client;
    public Date date;
    public ArrayList<Product> listOfProducts;
    public float totalValue;

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
