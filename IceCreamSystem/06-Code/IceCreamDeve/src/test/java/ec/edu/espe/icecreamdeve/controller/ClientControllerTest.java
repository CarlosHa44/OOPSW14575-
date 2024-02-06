package ec.edu.espe.icecreamdeve.controller;

import ec.edu.espe.icecreamdeve.model.Client;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Granda
 */
public class ClientControllerTest {

    public ClientControllerTest() {
    }

    /**
     * Test of addClient method, of class ClientController.
     */
   @Test
    public void testAddClient() {
        System.out.println("testAddClient");
        ArrayList<Client> clients = new ArrayList<>();
        Client clientToAdd = new Client(3, "Jane Doe", "jane@gmail.com", "987654321", false, true);
        Client result = ClientController.addClient(clients);
        assertEquals(clientToAdd, result);
    }

    /**
     * Test of editClient method, of class ClientController.
     */
    @Test
    public void testEditClient() {
        System.out.println("editClient");
        Client client = new Client(10, "Jane Doe", "jane@gmail.com", "987654321", false, true);
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client(3, "Jane Doe", "jane@gmail.com", "987654321", false, true));
        ClientController.editClient(client, clients);
    }

    /**
     * Test of menuClient method, of class ClientController.
     */
    @Test
    public void testMenuClient() {
        ByteArrayInputStream in = new ByteArrayInputStream("6\n5\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ArrayList<Client> clients = new ArrayList<>();
        ClientController.menuClient(clients);
        assertTrue(outContent.toString().contains("Invalid option. Please try again."));
        System.setIn(System.in);
        System.setOut(System.out);
    }

}
