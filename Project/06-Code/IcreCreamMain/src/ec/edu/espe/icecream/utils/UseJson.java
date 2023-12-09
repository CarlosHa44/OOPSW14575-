
package ec.edu.espe.icecream.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.icecream.model.Product;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class UseJson {
    public ArrayList<Product> readFile(String fileAdress){
        ArrayList<Product> products = new ArrayList<>();
        try{
              FileReader fileReader = new FileReader(fileAdress);
              Type type = new TypeToken<ArrayList<Product>>(){}.getType();
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              
              products=gson.fromJson(fileReader, type);
              fileReader.close();
              
          }catch(FileNotFoundException e){
              System.err.println("Error in creating the File Reader Object");
          }catch(IOException e){
              throw new RuntimeException(e);
          }
        return products;
    }
    
    public void writeFile(String fileAdress,ArrayList<Product> products){         
          try{
              FileWriter fileWriter = new FileWriter(fileAdress);
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              gson.toJson(products,fileWriter);
              fileWriter.close();
          
          }catch(IOException e){
              throw new RuntimeException(e);
          }
    }
}
