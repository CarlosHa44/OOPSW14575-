
package ec.edu.espe.icecreamdeve.controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.icecreamdeve.model.Invoice;
import ec.edu.espe.icecreamdeve.model.Product;
import ec.edu.espe.icecreamdeve.utils.MDBManage;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Juan Granda
 */
public class InvoiceControllerTest {
    
    public InvoiceControllerTest() {
    }

    /**
     * Test of register method, of class InvoiceController.
     */
     @Test
    public void testRegister() {
        ArrayList<Product> boxes = null;
        Invoice invoice = new Invoice(1, new Date(), 0,boxes);

        MongoCollection<Invoice> mockCollection = mock(MongoCollection.class);

        doNothing().when(mockCollection).insertOne(invoice);

        when(MDBManage.getFromCollection(eq("Invoices"), eq(Invoice.class))).thenReturn(mockCollection);
        verify(mockCollection, times(1)).insertOne(invoice);
    }
    /**
     * Test of showInvoice method, of class InvoiceController.
     */
    @Test
    public void testShowInvoice() {
        System.out.println("showInvoice");
        Invoice Invoice = null;
        InvoiceController.showInvoice(Invoice);
    }

    /**
     * Test of getActualIdInvoice method, of class InvoiceController.
     */
    @Test
    public void testGetActualIdInvoice() {
        System.out.println("getActualIdInvoice");
        ArrayList<Invoice> invoices = null;
        InvoiceController instance = new InvoiceController();
        int expResult = 89;
        int result = instance.getActualIdInvoice(invoices);
        assertEquals(expResult, result);
    }

    /**
     * Test of findAllProducts method, of class InvoiceController.
     */
    @Test
    public void testFindAllProducts() {
        System.out.println("findAllProducts");
        InvoiceController instance = new InvoiceController();
        ArrayList<Invoice> expResult = null;
        ArrayList<Invoice> result = instance.findAllInvoices();
        assertEquals(expResult, result);

    }

    /**
     * Test of addAmount method, of class InvoiceController.
     */
    @Test
    public void testAddAmount() {
        System.out.println("addAmount");
        ArrayList<Product> products = null;
        int id = 1;
        int amount = 3;
        InvoiceController instance = new InvoiceController();
        instance.addAmount(products, id, amount);
    }

    /**
     * Test of addInvoice method, of class InvoiceController.
     */
   @Test
    public void testAddInvoice() {
        ArrayList<Invoice> invoices = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InvoiceController.addInvoice(invoices, products);
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("////////Factura////////////"));
        assertTrue(outContent.toString().contains("Nota de Venta: "));
        assertTrue(outContent.toString().contains("Date:"));
        assertTrue(outContent.toString().contains("////Listado de Productos/////"));
        assertTrue(outContent.toString().contains("Precio Final:"));

    }


    /**
     * Test of menuInvoice method, of class InvoiceController.
     */
    @Test
    public void testMenuInvoice() {
        System.out.println("menuInvoice");
        ArrayList<Invoice> invoices = null;
        ArrayList<Product> products = null;
        InvoiceController.menuInvoice(invoices, products);
    }
    
}
