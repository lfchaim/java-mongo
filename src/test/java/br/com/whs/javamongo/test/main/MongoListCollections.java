package br.com.whs.javamongo.test.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoListCollections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	private static void test1() {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

			MongoDatabase database = mongoClient.getDatabase("local");

			for (String name : database.listCollectionNames()) {

				System.out.println("Collection: "+name);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
