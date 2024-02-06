
package ec.edu.espe.icecreamdeve.utils;

import static ec.edu.espe.icecreamdeve.utils.Authenticator.authenticateUser;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Granda
 */
public class AuthenticatorTest {
    
    public AuthenticatorTest() {
    }

    /**
     * Test of authenticateUser method, of class Authenticator.
     */
   @Test
    public void testAuthenticateUser_SuccessfulAuthentication() {
        String input = "yourUsername\nyourPassword\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        boolean result = authenticateUser(scanner);
        assertTrue(result);
    }

    @Test
    public void testAuthenticateUser_FailedAuthentication() {
        String input = "invalidUsername\ninvalidPassword\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        boolean result = authenticateUser(scanner);
        assertFalse(result);
    }
}
