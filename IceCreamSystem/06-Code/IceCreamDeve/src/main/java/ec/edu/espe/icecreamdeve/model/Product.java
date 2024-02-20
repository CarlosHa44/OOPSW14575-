    package ec.edu.espe.icecreamdeve.model;

    import java.util.Scanner;

    /**
     *
     * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
     */
    public class Product{

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
        static Scanner scan = new Scanner(System.in);

        public Product(){}


        @Override
        public String toString() {
            return "cantidad: " + amount + " " + name + " cost=" + cost + "\n";
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
