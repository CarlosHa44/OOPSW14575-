
package ec.edu.espe.icecreamdeve.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Carlos
 */
public abstract class MDBManage {

    public static MongoDatabase connectToDataBase(){
        String connectionString="mongodb+srv://carlos:carlosha@cluster0.5w2v3gl.mongodb.net/";
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

        MongoClient mongoClient = MongoClients.create(clientSettings);
        MongoDatabase db = mongoClient.getDatabase("ICE_DEV");
        return db;
    }
    
    public static <T> MongoCollection<T> getFromCollection(String collectionName,Class type){
       MongoDatabase db=connectToDataBase();
       MongoCollection<T> collectionDB=db.getCollection(collectionName, type);       
       return collectionDB;
    }
    
    public abstract void register(Object objeto);
    
}
