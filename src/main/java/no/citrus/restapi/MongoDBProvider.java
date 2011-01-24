package no.citrus.restapi;

import java.net.UnknownHostException;

import no.citrus.restapi.configuration.Configuration;
import no.citrus.restapi.configuration.PropertiesHolder;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDBProvider {
	private static MongoDBProvider INSTANCE;
	private DB db = null;
	
	private MongoDBProvider() throws MongoException, UnknownHostException {
		Configuration config = new Configuration(PropertiesHolder.getInstance());
		Mongo mongo = new Mongo(config.getDatabaseURL(), config.getDatabasePort());
		db = mongo.getDB(config.getDatabaseName());
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
