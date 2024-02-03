
package ec.edu.espe.icecreamdeve.utils;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.icecreamdeve.model.Client;
import java.util.List;
import org.bson.Document;
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


        public static void editClient(Client client) {
            connectToDataBase();
            MongoDatabase database = mongoClient.getDatabase(DATABASE);
            MongoCollection<Document> collection = database.getCollection("Client");
            Document filter = new Document("id", client.getId());
            Document updateItem = new Document("$set", new Document()
                    .append("Name", client.getName())
                    .append("Email", client.getEmail())
                    .append("Cellphone Number", client.getCellphoneNumber())
                    .append("Is North", client.getIsNorth())
                    .append("Is Majority", client.getIsMajority()));

            collection.updateOne(filter, updateItem);
        }
        public static void deleteClient(int id) {
            connectToDataBase();
            MongoDatabase database = mongoClient.getDatabase(DATABASE);
            MongoCollection<Document> collection = database.getCollection("Client");
            Document filter = new Document("id", id);
            collection.deleteOne(filter);
        }
        public static <T> MongoCollection<T> getFromCollection(String collectionName, Class<T> type) {
            MongoDatabase db = connectToDataBase();
            return db.getCollection(collectionName, type);
        }
    public abstract void register(Object objeto);
    
}

