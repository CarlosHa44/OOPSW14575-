
package ec.edu.espe.icecream.model;

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

    public Client(int id, String name, String email, String cellphoneNumber, boolean isNorth, boolean isMajority) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.isNorth = isNorth;
        this.isMajority = isMajority;
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
