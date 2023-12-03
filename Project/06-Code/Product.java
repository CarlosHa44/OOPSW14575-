


/**
 *
 * @author JuanGranda,Error 404,DCCO-ESPE
 */

public class Product {
    private int id;
    private String name;
    private float cost;
    private String dateCurrent;
    private String dueDate;



    public Product(int id, String name, float cost, String dateCurrent, String dueDate) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.dateCurrent = dateCurrent;
        this.dueDate = dueDate;
        
    }

    @Override
    public String toString() {
        return "Product[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost + ", dateCurrent=" + dateCurrent + ", dueDate= " 
                + dueDate +
                ']';
    }
}
