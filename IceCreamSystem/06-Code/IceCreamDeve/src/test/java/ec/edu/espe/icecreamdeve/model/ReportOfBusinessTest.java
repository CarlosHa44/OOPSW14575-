
package ec.edu.espe.icecreamdeve.model;

import java.util.ArrayList;
import static java.util.Map.entry;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Granda
 */
public class ReportOfBusinessTest {

    private String entry;
    
    public ReportOfBusinessTest() {
    }

    /**
     * Test of createEntrys method, of class ReportOfBusiness.
     */
    @Test
    public void testCreateEntrys() {
        System.out.println("createEntrys");
        ArrayList<SaleNote> saleNotes = null;
        int yearReport = 2024;
        int monthReport = 10;
        float expResult = 4.0F;
        float result = ReportOfBusiness.createEntrys(saleNotes, yearReport, monthReport);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSales method, of class ReportOfBusiness.
     */
    @Test
    public void testCreateSales() {
        System.out.println("createSales");
        ArrayList<Invoice> invoices = null;
        int yearReport = 2024;
        int monthReport = 8;
        float expResult = 8.0F;
        float result = ReportOfBusiness.createSales(invoices, yearReport, monthReport);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAReport method, of class ReportOfBusiness.
     */
    @Test
    public void testCreateAReport() {
        System.out.println("createAReport");
        ArrayList<ReportOfBusiness> reports = null;
        ArrayList<SaleNote> saleNotes = null;
        ArrayList<Invoice> invoices = null;
        ReportOfBusiness expResult = null;
        ReportOfBusiness result = ReportOfBusiness.createAReport(reports, saleNotes, invoices);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuReportBusinness method, of class ReportOfBusiness.
     */
    @Test
    public void testMenuReportBusinness() {
        System.out.println("menuReportBusinness");
        ArrayList<ReportOfBusiness> reports = null;
        ReportOfBusiness.menuReportBusinness(reports);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEntry method, of class ReportOfBusiness.
     */
    @Test
    public void testGetEntry() {
        System.out.println("getEntry");
        ReportOfBusiness instance = null;
        float expResult = 1.0F;
        float result = instance.getEntry();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setEntry method, of class ReportOfBusiness.
     */
    @Test
    public void testSetEntry() {
        System.out.println("setEntry");
        float entry = 8.0F;
        ReportOfBusiness instance = null;
        instance.setEntry(entry);
    }

    /**
     * Test of getSale method, of class ReportOfBusiness.
     */
    @Test
    public void testGetSale() {
        System.out.println("getSale");
        ReportOfBusiness instance = null;
        float expResult = 34.0F;
        float result = instance.getSale();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setSale method, of class ReportOfBusiness.
     */
    @Test
    public void testSetSale() {
        System.out.println("setSale");
        float sale = 5.0F;
        ReportOfBusiness instance = null;
        instance.setSale(sale);
    }

    /**
     * Test of getProfits method, of class ReportOfBusiness.
     */
    @Test
    public void testGetProfits() {
        System.out.println("getProfits");
        ReportOfBusiness instance = null;
        float expResult = 8.0F;
        float result = instance.getProfits();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setProfits method, of class ReportOfBusiness.
     */
    @Test
    public void testSetProfits() {
        System.out.println("setProfits");
        float profits = 66.0F;
        ReportOfBusiness instance = null;
        instance.setProfits(profits);
    }

    /**
     * Test of toString method, of class ReportOfBusiness.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ReportOfBusiness instance = null;
        char profits = 0;
        String sale = null;
        String expResult = "ReportOfBusiness" + "-entry=" + entry + ", sale=" + sale + ", profits=" + profits + '\n';
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
