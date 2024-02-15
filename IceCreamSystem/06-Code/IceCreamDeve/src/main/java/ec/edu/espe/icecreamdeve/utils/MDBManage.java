
package ec.edu.espe.icecreamdeve.utils;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import ec.edu.espe.icecreamdeve.model.Client;
import java.util.ArrayList;
import org.bson.Document;
import com.mongodb.client.result.UpdateResult;
import ec.edu.espe.icecreamdeve.model.Product;
import ec.edu.espe.icecreamdeve.model.SaleNote;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Carlos
 */
public abstract class MDBManage {
    private static final String DATABASE = "ICE_DEV";
     private static MongoClient mongoClient;
    static {
        connectToDataBase();
    }
     public static MongoDatabase connectToDataBase() {
        String connectionString = "mongodb+srv://mateo:mateo@cluster0.5w2v3gl.mongodb.net/";
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .codecRegistry(codecRegistry)
                .build();

        mongoClient = MongoClients.create(clientSettings);

        return mongoClient.getDatabase(DATABASE);
    }
    public static void registerClient(Client client) {
        MongoDatabase database = connectToDataBase();
        MongoCollection<Document> collection = database.getCollection("Client");
        Gson gson = new Gson();

        Document clientDocument = Document.parse(gson.toJson(client));
        collection.insertOne(clientDocument);
    }


    public static void editClientMG(Client client, ArrayList<Client> clients) {
        connectToDataBase();
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<Document> collection = database.getCollection("Client");

        
        Document filter = new Document("id", client.getId());

        
        Document updatedDocument = new Document("id", client.getId())
                .append("Name", client.getName())
                .append("Email", client.getEmail())
                .append("CellphoneNumber", client.getCellphoneNumber())
                .append("Is North", client.getIsNorth())
                .append("Is Majority", client.getIsMajority());

        
        UpdateResult result = collection.replaceOne(filter, updatedDocument);

        if (result.getModifiedCount() > 0) {
            System.out.println("Client edited successfully in MongoDB.");
        } else {
            System.out.println("Client not found or no changes were made in MongoDB.");
        }

        
        updateClientInList(client, clients);
    }


    
    private static void updateClientInList(Client updatedClient, ArrayList<Client> clients) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId() == updatedClient.getId()) {
                clients.set(i, updatedClient);
                break;
            }
        }
    }

    public static void deleteClient(int id, ArrayList<Client> clients) {
        connectToDataBase();
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<Document> collection = database.getCollection("Client");

        Document filter = new Document("id", id);
        DeleteResult result = collection.deleteOne(filter);

        if (result.getDeletedCount() > 0) {
            System.out.println("Client deleted successfully from MongoDB.");

            
            Client deletedClient = findClientById(clients, id);
            if (deletedClient != null) {
                clients.remove(deletedClient);
                System.out.println("Client deleted successfully from the local list.");
            } else {
                System.out.println("Error: Client not found in the local list.");
            }
        } else {
            System.out.println("Client not found in MongoDB or no changes were made.");
        }
    }
    private static Client findClientById(ArrayList<Client> clients, int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }
     public static void registerSaleNote(SaleNote saleNote, ArrayList<SaleNote> saleNotes) {
        MongoCollection<SaleNote> collection = getFromCollection("SaleNote", SaleNote.class);

       
        collection.insertOne(saleNote);

       
        saleNotes.add(saleNote);
    }

    public static void deleteSaleNote(int id, ArrayList<SaleNote> saleNotes) {
        MongoCollection<SaleNote> collection = getFromCollection("SaleNote", SaleNote.class);

        Document filter = new Document("id", id);
        DeleteResult result = collection.deleteOne(filter);

        if (result.getDeletedCount() > 0) {
            System.out.println("Sale Note deleted successfully from MongoDB.");

           
            SaleNote deletedSaleNote = findSaleNoteById(saleNotes, id);
            if (deletedSaleNote != null) {
                saleNotes.remove(deletedSaleNote);
                System.out.println("Sale Note deleted successfully from the local list.");
            } else {
                System.out.println("Error: Sale Note not found in the local list.");
            }
        } else {
            System.out.println("Sale Note not found in MongoDB or no changes were made.");
        }
    }

    private static SaleNote findSaleNoteById(ArrayList<SaleNote> saleNotes, int id) {
        for (SaleNote saleNote : saleNotes) {
            if (saleNote.getId() == id) {
                return saleNote;
            }
        }
        return null;
    }
    public static void updateProductQuantity(String productName, int soldQuantity) {
        MongoCollection<Product> productCollection = getFromCollection("Products", Product.class);
        // Buscar el producto por nombre
        Product product = productCollection.find(eq("name", productName)).first();

        if (product != null) {
            // Actualizar la cantidad restando la cantidad vendida
            int updatedQuantity = product.getAmount() - soldQuantity;
            // Verificar que la cantidad no sea negativa
            updatedQuantity = Math.max(updatedQuantity, 0);

            // Actualizar en la base de datos
            productCollection.updateOne(eq("name", productName), set("amount", updatedQuantity));
        }
    }

        public static <T> MongoCollection<T> getFromCollection(String collectionName, Class<T> type) {
            MongoDatabase db = connectToDataBase();
            return db.getCollection(collectionName, type);
        }
    public abstract void register(Object object);
    
}

