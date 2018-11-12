package idv.mission.example.MongoDB_Test;

import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * RDBMS        MongoDB
 * Database     Database
 * Table        Collection
 * Tuple/Row    Document
 * column       Field
 * Table Join   Embedded Documents
 */
public class App {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("test");
        database.getCollection("MissionTest");
        MongoCollection<Document> collection = database.getCollection("MissionTest");

        Document document = new Document();
        document.append("User", "Mission");

        // collection.insertOne(document);

        MongoIterable<String> collectionNames = database.listCollectionNames();
        Iterator<String> iterator = collectionNames.iterator();
        while (iterator.hasNext()) {
            System.out.println("Collection: " + iterator.next());
        }

        FindIterable<Document> documents = collection.find();
        Iterator<Document> iterator2 = documents.iterator();
        while (iterator2.hasNext()) {
            Document foundDocument = iterator2.next();
            String user = (String) foundDocument.get("User");
            System.out.println("Document: " + foundDocument);
            System.out.println("User: " + user);
        }

        client.close();
        System.out.println("End");
    }
}
