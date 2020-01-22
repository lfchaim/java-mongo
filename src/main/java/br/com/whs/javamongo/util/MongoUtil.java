package br.com.whs.javamongo.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;

import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

// Fonte: http://zetcode.com/java/mongodb/
public class MongoUtil {

	@Value("${spring.data.mongodb.uri}")
    private static String mongoUri;
	
	public static MongoClient loadMongoClient() {
		System.out.println("mongoUri: "+mongoUri);
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		return mongoClient;
	}
	
	public static List<String> listDatabaseNames( MongoClient mongoClient ) {
		List<String> ret = new ArrayList<String>();
		MongoIterable<String> list = mongoClient.listDatabaseNames();
		if( list == null )
			return ret;
		for( String it : list ) {
			ret.add(it);
		}
		return ret;
	}
	
	public static MongoDatabase loadMongoDatabase( MongoClient mongoClient, String databaseName ) {
		MongoDatabase database = mongoClient.getDatabase(databaseName);
		return database;
	}
	
	public static List<String> listCollection( MongoDatabase mongoDatabase ) {
		List<String> ret = new ArrayList<String>();
		MongoIterable<String> list = mongoDatabase.listCollectionNames();
		if( list == null )
			return ret;
		for( String it : list ) {
			ret.add(it);
		}
		return ret;
	}
	
	public static MongoCollection loadMongoCollection( MongoDatabase mongoDatabase, String collectionName ) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		return collection;
	}
	
	public static void createCollection( MongoDatabase mongoDatabase, String collectionName ) {
		try {
			mongoDatabase.createCollection(collectionName);
        } catch (MongoCommandException e) {
        	mongoDatabase.getCollection(collectionName).drop();
        }
	}
	
	public static void deleteCollection( MongoDatabase mongoDatabase, String collectionName ) {
		try {
			mongoDatabase.getCollection(collectionName).drop();
        } catch (MongoCommandException e) {

        }
	}
	
	public static List<Document> findAll( MongoCollection mongoCollection ){
		List<Document> ret = null;
		 try (MongoCursor<Document> cur = mongoCollection.find().iterator()) {
             while (cur.hasNext()) {
            	 if( ret == null )
            		 ret = new ArrayList<Document>();
                 Document doc = cur.next();
                 ret.add(doc);
             }
		 }
		 return ret;
	}
	
	public static void insertOne( MongoCollection mongoCollection, Map<String,Object> map ) {
		Document doc = new Document();
		Iterator<String> it = map.keySet().iterator();
		while( it.hasNext() ) {
			String key = it.next();
			doc.put(key, map.get(key));
		}
		if( map.get("createdDate") == null )
			doc.put("createdDate", DateUtil.format(new Date(), "yyyy-MM-dd'T'HH:mm:ss'Z'"));
		mongoCollection.insertOne(doc);
	}
	
	public static void insertMany( MongoCollection mongoCollection, List<Document> list ) {
		mongoCollection.insertMany(list);
	}
}
