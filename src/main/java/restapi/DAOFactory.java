package restapi;


public abstract class DAOFactory {
	
	public abstract MeasureDAO getMeasureDAO();
	
	public static DAOFactory getDatabase() {
		switch (Global.DATABASETYPE) {
		case Mongo:
			return new MongoDAOFactory();
		default:
			return null;
		}
	}
}
