package ec.espe.edu.model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateo
 */
public class GestionClient {
     private final List<Client> listClients;

    // Constructor
    public GestionClient() {
        this.listClients = new ArrayList<>();
    }


        // Método para agregar un nuevo cliente a la lista
    public void agregarCliente(Client client) {
        listClients.add(client);
        System.out.println("Cliente agregado con éxito: " + client);
    }
}
