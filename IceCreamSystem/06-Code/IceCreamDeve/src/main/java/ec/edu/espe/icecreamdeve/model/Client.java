package ec.edu.espe.icecreamdeve.model;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.icecreamdeve.utils.MDBManage;
import ec.edu.espe.icecreamdeve.utils.UseJson;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
 */
public class Client {

    private int id;
    private String name;
    private String email;
    private String cellphoneNumber;
    private boolean isNorth;
    private boolean isMajority;

    public Client(int id, String name, String email, String cellphoneNumber, boolean isNorth, boolean isMajority) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.isNorth = isNorth;
        this.isMajority = isMajority;
    }
    
    public Client(){}


    public static Client findClientById(ArrayList<Client> clients, int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }
    private static int generateID(ArrayList<Client> clients) {
        int actualId = 0;
        for (Client currentClient : clients) {
            actualId = currentClient.getId();
        }
        return actualId + 1;
    }
            public static Client addClient(ArrayList<Client> clients) {
        Scanner scan = new Scanner(System.in);
        UseJson<Client> jsonUtilClients = new UseJson<>();
        clients = jsonUtilClients.readFile("clientdata.json", new TypeToken<ArrayList<Client>>() {
        }.getType());
        int newID = generateID(clients);
        String name = "";
        String email = "";
        String cellphoneNumber = "";
        boolean isNorth = false;
        boolean isMajority = false;
        boolean inputValid;

        do {
            try {

                do {
                    System.out.println("Enter the new name");
                    name = scan.nextLine();
                    if (!name.matches("[a-zA-Z]+")) {
                        System.out.println("Please, Enter the customer name.");
                        inputValid = false;
                    } else {
                        inputValid = true;
                    }
                } while (!inputValid);
                System.out.println("Enter the customer's email");
                email = scan.nextLine();
                System.out.println("Enter the customer's phone number");
                cellphoneNumber = scan.nextLine();
                System.out.println("¿The client is in the north? (true/false)");
                isNorth = scan.nextBoolean();
                System.out.println("¿The client is the majority? (true/false)");
                isMajority = scan.nextBoolean();
                inputValid = true;
            } catch (InputMismatchException e) {
                System.out.println("An error occurred");
                inputValid = false;
                scan.nextLine();
            }
        } while (!inputValid);
        scan.nextLine();
        Client newClient = new Client(newID, name, email, cellphoneNumber, isNorth, isMajority);
        MDBManage.registerClient(newClient);
        clients.add(newClient);
      jsonUtilClients.writeFile("clientdata.json", clients);
        return  newClient;
        
    }



    @Override
    public String toString() {
        return String.format("\n%-5d %-15s %-30s %-16s %-10b %b",
                getId(), getName(), getEmail(), getCellphoneNumber(), isIsNorth(), isIsMajority());
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

    /**
     * @return the isNorth
     */
    public boolean isIsNorth() {
        return isNorth;
    }

    /**
     * @param isNorth the isNorth to set
     */
    public void setIsNorth(boolean isNorth) {
        this.isNorth = isNorth;
    }

    /**
     * @return the isMajority
     */
    public boolean isIsMajority() {
        return isMajority;
    }

    /**
     * @param isMajority the isMajority to set
     */
    public void setIsMajority(boolean isMajority) {
        this.isMajority = isMajority;
    }
    
    public boolean getIsNorth() {
        return isNorth;
    }

    public boolean getIsMajority() {
        return isMajority;
    }


}
