package ec.edu.espe.icecreamdeve.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
 */
public class Invoice{

    private static Scanner scan = new Scanner(System.in);
    private int id;
    private Date dateI;
    private float value;
    private ArrayList<Product> boxes;

    public Invoice(int id, Date dateI, float value, ArrayList<Product> boxes) {
        this.id = id;
        this.dateI = dateI;
        this.value = value;
        this.boxes = boxes;
    }
    
    public Invoice(){}

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

    public ArrayList<Product> getBoxes() {
        return boxes;
    }

    public void setBoxes(ArrayList<Product> boxes) {
        this.boxes = boxes;
    }

    public static Scanner getScan() {
        return scan;
    }

    public static void setScan(Scanner aScan) {
        scan = aScan;
    }

   
    }

