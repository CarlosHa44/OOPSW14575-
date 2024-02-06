
package ec.edu.espe.icecreamdeve.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Granda
 */
public class InvoiceTest {
    
    public InvoiceTest() {
    }

    /**
     * Test of getId method, of class Invoice.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Invoice instance = new Invoice();
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Invoice.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 2;
        Invoice instance = new Invoice();
        instance.setId(id);
    }

    /**
     * Test of getDateI method, of class Invoice.
     */
    @Test
    public void testGetDateI() {
        System.out.println("getDateI");
        Invoice instance = new Invoice();
        Date expResult = new Date();
        Date result = instance.getDateI();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateI method, of class Invoice.
     */
    @Test
    public void testSetDateI() {
        System.out.println("setDateI");
        Date dateI = new Date();
        Invoice instance = new Invoice();
        assertEquals(dateI, instance);
    }

    /**
     * Test of getValue method, of class Invoice.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Invoice instance = new Invoice();
        float expResult = 4.0F;
        float result = instance.getValue();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setValue method, of class Invoice.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        float value = 3.0F;
        Invoice instance = new Invoice();
        instance.setValue(value);
    }

    /**
     * Test of getBoxes method, of class Invoice.
     */
    @Test
    public void testGetBoxes() {
        System.out.println("getBoxes");
        Invoice instance = new Invoice();
        ArrayList<Product> expResult = null;
        ArrayList<Product> result = instance.getBoxes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBoxes method, of class Invoice.
     */
    @Test
    public void testSetBoxes() {
        System.out.println("setBoxes");
        ArrayList<Product> boxes = new ArrayList<>();
        Invoice instance = new Invoice();
        instance.setBoxes(boxes);
    }

    /**
     * Test of getScan method, of class Invoice.
     */
    @Test
    public void testGetScan() {
        System.out.println("getScan");
        Scanner expResult = null;
        Scanner result = Invoice.getScan();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScan method, of class Invoice.
     */
    @Test
    public void testSetScan() {
        System.out.println("setScan");
        Scanner aScan = null;
        Invoice.setScan(aScan);
    }
    
}
