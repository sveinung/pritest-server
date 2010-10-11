package restapi;

import restapi.model.DatabaseType;

public class Global {
	public static final DatabaseType DATABASETYPE = DatabaseType.Mongo;
	public static final int DB_PORT = 27017;
	public static final String DB_URL = "localhost";
	public static final String DB_NAME = "citrus";
}
