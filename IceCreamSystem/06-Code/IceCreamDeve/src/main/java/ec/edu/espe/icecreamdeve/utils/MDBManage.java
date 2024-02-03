
package ec.edu.espe.icecreamdeve.utils;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import ec.edu.espe.icecreamdeve.model.Client;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.result.UpdateResult;
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

        // Utiliza el campo "id" como identificador
        Document filter = new Document("id", client.getId());

        // Convierte el objeto Client a Document para reemplazar el documento completo
        Document updatedDocument = new Document("id", client.getId())
                .append("Name", client.getName())
                .append("Email", client.getEmail())
                .append("CellphoneNumber", client.getCellphoneNumber())
                .append("Is North", client.getIsNorth())
                .append("Is Majority", client.getIsMajority());

        // Reemplaza todo el documento en lugar de actualizar campos específicos
        UpdateResult result = collection.replaceOne(filter, updatedDocument);

        if (result.getModifiedCount() > 0) {
            System.out.println("Client edited successfully in MongoDB.");
        } else {
            System.out.println("Client not found or no changes were made in MongoDB.");
        }

        // Actualizar también en la lista local
        updateClientInList(client, clients);
    }


    // Agrega este método para actualizar el cliente en la lista local
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

            // También eliminamos el cliente de la lista local
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
        public static <T> MongoCollection<T> getFromCollection(String collectionName, Class<T> type) {
            MongoDatabase db = connectToDataBase();
            return db.getCollection(collectionName, type);
        }
    public abstract void register(Object objeto);
    
}

