package ec.edu.espe.icecreamdeve.model;
import java.util.ArrayList;


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


    public static Client findClientById(ArrayList<Client> clients, int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
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
