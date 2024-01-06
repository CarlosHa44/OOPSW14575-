
package ec.edu.espe.icecream.utils;

import ec.edu.espe.icecream.model.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Juan Granda
 */
public class UseCsv {
    public static void saveToCSV(ArrayList<Product> products, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("ID;Amount;Name;Cost\n");

            for (Product product : products) {
                writer.write(String.format("%d;%d;%s;%.2f\n",
                        product.getId(), product.getAmount(), product.getName(), product.getCost()));
            }

            System.out.println("Data saved to CSV file successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

  
    
}
