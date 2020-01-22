package br.com.whs.javamongo.test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoReadAll {

    public static void main(String[] args) {

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            MongoDatabase database = mongoClient.getDatabase("local");

            MongoCollection<Document> collection = database.getCollection("startup_log");

            try (MongoCursor<Document> cur = collection.find().iterator()) {

                while (cur.hasNext()) {

                    Document doc = cur.next();
                    Map<String, Object> map = doc;
                    
                    System.out.println("DOCUMENT: "+map.toString());
                    
                    //List items = new ArrayList<>(doc.values());
                    //System.out.printf("%s: %s%n", items.get(1), items.get(2));
                }
            }
        }
    }
}