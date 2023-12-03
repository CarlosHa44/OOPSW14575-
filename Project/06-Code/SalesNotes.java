
package salesnortes;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;


public class SalesNotes {
   private Collection<Client> clients;
   private Date date; 
   private Collection<Product> numberOfProducts;
   private float totalValue;

    public SalesNotes(Collection<Client> clients, Date date, Collection<Product> numberOfProducts, float totalValue) {
        this.clients = clients;
        this.date = date;
        this.numberOfProducts = numberOfProducts;
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "SalesNortes{" + "clients=" + clients + ", date=" + date + ", numberOfProducts=" + numberOfProducts + ", totalValue=" + totalValue + '}';
    }
     public Collection<Client> getClients() {
        return clients;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Collection<Product> getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Collection<Product> numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }
    
    private float calculateTotalValue() {
        float total = 0;
        for (Product numberOfProducts : numberOfProducts) {
            total += numberOfProducts.getCost();
        }
        return total;
    }
     public static void main(String[] args) {
       
       Collection<Client> clients = new ArrayList<>();
       clients.add(new Client());
        

       Collection<Product> numberOfProducts = new ArrayList<>();
       numberOfProducts.add(new Product(30.0f));
      
       Date date = new Date();

       SalesNotes sales = new SalesNotes(clients, date, numberOfProducts,1 );
       System.out.println(sales);
    }

   
}
