package br.com.whs.javamongo.test.main;

import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoCommand {

    public static void main(String[] args) {

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            MongoDatabase database = mongoClient.getDatabase("local");

            Document stats = database.runCommand(new Document("dbstats", 1));

            for (Map.Entry<String, Object> set : stats.entrySet()) {

                System.out.format("%s: %s%n", set.getKey(), set.getValue());
            }
        }
    }
}