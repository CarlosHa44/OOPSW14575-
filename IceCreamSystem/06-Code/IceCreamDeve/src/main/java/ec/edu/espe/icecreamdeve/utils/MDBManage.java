
package ec.edu.espe.icecreamdeve.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Carlos
 */
public class MDBManage {
    private MongoClient mongoClient;

    public MDBManage() {
        this.mongoClient = connect();
    }

    public MongoClient connect() {
        String MONGODB_URI = "mongodb+srv://carlos:carlosha@cluster0.5w2v3gl.mongodb.net/?retryWrites=true&w=majority ";

        MongoClient mongoClient = MongoClients.create(MONGODB_URI);
        return mongoClient;
    }

    public MongoCollection<Document> connectToCollection(String collectionName) {
        MongoDatabase db = mongoClient.getDatabase("ICE_DEV");
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return collection;
    }
}
