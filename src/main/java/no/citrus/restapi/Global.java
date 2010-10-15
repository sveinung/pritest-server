package no.citrus.restapi;

import no.citrus.restapi.model.DatabaseType;

public class Global {
	public static final DatabaseType DATABASETYPE = DatabaseType.Mongo;
	public static final int DB_PORT = 27017;
	public static final String DB_URL = "127.0.0.1";
	public static final String DB_NAME = "citrus";
}
