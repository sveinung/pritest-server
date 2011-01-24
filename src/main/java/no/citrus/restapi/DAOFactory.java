package no.citrus.restapi;

import no.citrus.restapi.configuration.Configuration;
import no.citrus.restapi.configuration.PropertiesHolder;


public abstract class DAOFactory {
	
	public abstract MeasureDAO getMeasureDAO();
	
	public abstract ChangeDAO getChangeDAO();
	
	public abstract TestDataDAO getTestDataDAO();
	
	public abstract ChangeDataDAO getChangeDataDAO();
	
	public static DAOFactory getDatabase() {
		Configuration config = new Configuration(PropertiesHolder.getInstance());
		switch (config.getDatabaseType()) {
		case Mongo:
			return new MongoDAOFactory();
		default:
			return null;
		}
	}
}
