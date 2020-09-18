package util;

import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;

public class MongoConnection {

    private final String HOST = "localhost";
    private final String DB = "ecommerceMongo";
    private final int PORT = 27017;
    private final String USER = "cliente";
    private final String PWD = "mongo123";
    
    public MongoDatabase getDatabase() {

        //connection = new MongoClient(HOST, PORT);
        
        //return connection.getDatabase(DB);
        com.mongodb.client.MongoClient mongoClient = null;
        try {
            ConnectionString connString = new ConnectionString("mongodb://"+USER+":"+PWD+"@"+HOST+":"+PORT+"/"+DB);
            MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
            mongoClient = MongoClients.create(settings);
            System.out.println("MONGO CONNECTED.");

        } catch (MongoWriteException e) {
            e.printStackTrace ();
            System.out.println("CONNECT FAIL MWE: " + e.getMessage());
        } catch (MongoWriteConcernException e) {
           e.printStackTrace ();
           System.out.println("CONNECT FAIL MWCE: " + e.getMessage());
        } catch (MongoException e) {
           e.printStackTrace ();
           System.out.println("CONNECT FAIL ME: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("CONNECT FAIL E: " + e.getMessage());
        }
        
        return mongoClient.getDatabase(DB);
    }
}