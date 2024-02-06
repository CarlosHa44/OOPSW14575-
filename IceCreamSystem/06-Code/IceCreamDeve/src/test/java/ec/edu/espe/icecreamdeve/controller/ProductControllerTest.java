
package ec.edu.espe.icecreamdeve.controller;

import ec.edu.espe.icecreamdeve.model.Product;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Granda
 */
public class ProductControllerTest {
    
    public ProductControllerTest() {
    }

    /**
     * Test of register method, of class ProductController.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        Object object = null;
        ProductController instance = new ProductController();
        instance.register(object);
    }

    /**
     * Test of getActualId method, of class ProductController.
     */
    @Test
    public void testGetActualId() {
        System.out.println("getActualId");
        ArrayList<Product> products = null;
        ProductController instance = new ProductController();
        int expResult = 3;
        int result = instance.getActualId(products);
        assertEquals(expResult, result);
    }

    /**
     * Test of findAllProducts method, of class ProductController.
     */
    @Test
    public void testFindAllProducts() {
        System.out.println("findAllProducts");
        ProductController instance = new ProductController();
        ArrayList<Product> expResult = null;
        ArrayList<Product> result = instance.findAllProducts();
        assertEquals(expResult, result);
    }

    /**
     * Test of menuProduct method, of class ProductController.
     */
    @Test
    public void testMenuProduct() {
        System.out.println("menuProduct");
        ArrayList<Product> products = null;
        ProductController.menuProduct(products);
    }
    
}
