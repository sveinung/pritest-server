package no.citrus.restapi;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDBProvider {
	private static MongoDBProvider INSTANCE;
	private DB db = null;
	
	private MongoDBProvider() throws MongoException, UnknownHostException {
		Mongo mongo = new Mongo(Global.DB_URL, Global.DB_PORT);
		db = mongo.getDB(Global.DB_NAME);
	}
	
	public static MongoDBProvider getInstance() throws MongoException, UnknownHostException {
		if (INSTANCE == null) {
			INSTANCE = new MongoDBProvider();
		}
		return INSTANCE;
	}
	
	public DB getDB() {
		return db;
	}
}
