
package ec.edu.espe.icecreamdeve.controller;

import ec.edu.espe.icecreamdeve.model.Client;
import ec.edu.espe.icecreamdeve.model.Product;
import ec.edu.espe.icecreamdeve.model.SaleNote;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Granda
 */
public class SaleNoteControllerTest {
    
    public SaleNoteControllerTest() {
    }

    /**
     * Test of getActualId method, of class SaleNoteController.
     */
    @Test
    public void testGetActualId() {
        System.out.println("getActualId");
        ArrayList<SaleNote> saleNotes = null;
        int expResult = 55;
        int result = SaleNoteController.getActualId(saleNotes);
        assertEquals(expResult, result);
    }

    /**
     * Test of showSaleNote method, of class SaleNoteController.
     */
    @Test
    public void testShowSaleNote() {
        System.out.println("showSaleNote");
        SaleNote saleNote = null;
        SaleNoteController.showSaleNote(saleNote);
    }

    /**
     * Test of createSaleNote method, of class SaleNoteController.
     */
    @Test
    public void testCreateSaleNote() {
        System.out.println("createSaleNote");
        ArrayList<Client> clients = null;
        ArrayList<Product> products = null;
        ArrayList<SaleNote> saleNotes = null;
        SaleNote expResult = null;
        SaleNote result = SaleNoteController.createSaleNote(clients, products, saleNotes);
        assertEquals(expResult, result);
    }

    /**
     * Test of deduceProduct method, of class SaleNoteController.
     */
    @Test
    public void testDeduceProduct() {
        System.out.println("deduceProduct");
        ArrayList<Product> products = null;
        int id = 1;
        int numberOfProducts = 52;
        SaleNoteController.deduceProduct(products, id, numberOfProducts);
    }

    /**
     * Test of menuSaleNote method, of class SaleNoteController.
     */
    @Test
    public void testMenuSaleNote() {
        System.out.println("menuSaleNote");
        ArrayList<Product> products = null;
        ArrayList<Client> clients = null;
        SaleNoteController.menuSaleNote(products, clients);
    }

    /**
     * Test of deleteSaleNote method, of class SaleNoteController.
     */
    @Test
    public void testDeleteSaleNote() {
        System.out.println("deleteSaleNote");
        ArrayList<SaleNote> saleNotes = null;
        SaleNoteController.deleteSaleNote(saleNotes);
    }
    
}
