package no.citrus.restapi.configuration;

import java.util.Map;

import no.citrus.restapi.configuration.Configurable;
import no.citrus.restapi.model.DatabaseType;

public class Configuration {

	private DatabaseType databasetype = null;
	private int dbPort = 0;
	private String dbUrl = null;
	private String dbName = null;

	public Configuration(Configurable config){
		Map<Object, Object> prop = config.getProperties();
		dbName = (String)prop.get("DB_Name");
		dbUrl = (String)prop.get("DB_URL");
		try {
			dbPort = Integer.parseInt((String)prop.get("DB_Port"));
		} catch (NumberFormatException e) {
			dbPort = 27017;
		}
		String dbtype = (String)prop.get("DB_Type");
		if(dbtype.equals(DatabaseType.Mongo.toString())){
			databasetype = DatabaseType.Mongo;
		}
	}

	public DatabaseType getDatabaseType(){
		return databasetype;
	}
	public String getDatabaseURL(){
		return dbUrl;
	}
	public String getDatabaseName(){
		return dbName;
	}
	public int getDatabasePort(){
		return dbPort;
	}
}