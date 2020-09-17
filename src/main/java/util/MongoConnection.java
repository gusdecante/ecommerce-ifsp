package util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private MongoClient connection = null;
    private MongoDatabase dtbase = null;
    private final String HOST = "localhost";
    private final String DB = "ecommerceMongo";
    private final int PORT = 27017;
    
    public MongoDatabase getDatabase() {

        connection = new MongoClient(HOST, PORT);
        return connection.getDatabase(DB);
    }
}