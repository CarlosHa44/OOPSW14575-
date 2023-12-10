
package ec.edu.espe.icecream.model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String cellphoneNumber;
    private boolean isNorth;
    private boolean isMajority;
    private static int lastID = 0;
    
    public Client(int id, String name, String email, String cellphoneNumber, boolean isNorth, boolean isMajority) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.isNorth = isNorth;
        this.isMajority = isMajority;
    }
    
        private static int generateID(ArrayList<Client> clients){
            int actualId=0;
            for(Client currentClient: clients){
               actualId=currentClient.getId();
            }
        return actualId+1;
    }
        public static Client addClient(ArrayList<Client> clients) {
        Scanner scan = new Scanner(System.in);
        int newID = generateID(clients);
        System.out.println("Ingrese el nombre del cliente");
        String name = scan.nextLine();
        System.out.println("Ingrese el email del cliente");
        String email = scan.nextLine();
        System.out.println("Ingrese el número de teléfono del cliente");
        String cellphoneNumber = scan.nextLine();
        System.out.println("¿El cliente está en el norte? (true/false)");
        boolean isNorth = scan.nextBoolean();
        System.out.println("¿El cliente es mayoritario? (true/false)");
        boolean isMajority = scan.nextBoolean();

        return new Client(newID, name, email, cellphoneNumber, isNorth, isMajority);
    }
    public static void editClient(ArrayList<Client> clients) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el id del cliente a editar");
        int idToEdit = scan.nextInt();
        scan.nextLine();

        for (Client client : clients) {
            if (client.getId() == idToEdit) {
                System.out.println("Ingrese el nuevo nombre");
                String newName = scan.nextLine();
                client.setName(newName);
                System.out.println("Ingrese el nuevo email");
                String newEmail = scan.nextLine();
                client.setEmail(newEmail);
                System.out.println("Ingrese el nuevo numero del telefono del cliente");
                String newPhone = scan.nextLine();
                client.setCellphoneNumber(newPhone);
                System.out.println("Ingrese si el cliente esta en el norte? (true/false)");
                boolean newNorth = scan.nextBoolean();
                client.setIsNorth(newNorth);
                System.out.println("¿El cliente es mayoritario edad? (true/false)");
                boolean newIsMajority = scan.nextBoolean();
                client.setIsMajority(newIsMajority);
                
                System.out.println("Cliente editado exitosamente.");
                return;
            }
        }

        System.out.println("Cliente no encontrado.");
    }

    @Override
    public String toString() {
        return "Client{" + 
                "id=" + getId() + 
                ", name=" + getName() + 
                ", email=" + getEmail() + 
                ", cellphoneNumber=" + getCellphoneNumber() + 
                ", isNorth=" + isIsNorth() + 
                ", isMajority=" + isIsMajority() + 
                '}';
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getCellphoneNumber() {
        return cellphoneNumber;
    }


    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public boolean isIsNorth() {
        return isNorth;
    }


    public void setIsNorth(boolean isNorth) {
        this.isNorth = isNorth;
    }


    public boolean isIsMajority() {
        return isMajority;
    }


    public void setIsMajority(boolean isMajority) {
        this.isMajority = isMajority;
    }
    
    
}
