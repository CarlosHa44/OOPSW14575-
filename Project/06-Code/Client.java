
package ec.espe.edu.model;


/**
 *
 * @author mateo
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String cellphoneNumber;
    private boolean ifsouth;
    private boolean ifmajoritu;
      
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the cellphoneNumber
     */
    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    /**
     * @param cellphoneNumber the cellphoneNumber to set
     */
    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    /**
     * @return the ifsouth
     */
    public boolean isIfsouth() {
        return ifsouth;
    }

    /**
     * @param ifsouth the ifsouth to set
     */
    public void setIfsouth(boolean ifsouth) {
        this.ifsouth = ifsouth;
    }

    /**
     * @return the ifmajoritu
     */
    public boolean isIfmajoritu() {
        return ifmajoritu;
    }

    /**
     * @param ifmajoritu the ifmajoritu to set
     */
    public void setIfmajoritu(boolean ifmajoritu) {
        this.ifmajoritu = ifmajoritu;
    }

    public Client(int id, String name, String email, String cellphoneNumber, boolean ifsouth, boolean ifmajoritu) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.ifsouth = ifsouth;
        this.ifmajoritu = ifmajoritu;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name=" + name + ", email=" + email + ", cellphoneNumber=" + cellphoneNumber + ", ifsouth=" + ifsouth + ", ifmajoritu=" + ifmajoritu + '}';
    }
    
}
