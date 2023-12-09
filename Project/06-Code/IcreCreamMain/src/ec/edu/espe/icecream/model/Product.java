package ec.edu.espe.icecream.model;

/**
 *
 * @author Carlos
 */
public class Product {
    private int id;
    private int amount;
    private String name;
    private float cost;

    public Product(int id, int amount, String name, float cost) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "\n\tid=" + getId() + "\n\tamount=" + getAmount() + "\n\tname=" + getName() + "\n\tcost=" + getCost();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
