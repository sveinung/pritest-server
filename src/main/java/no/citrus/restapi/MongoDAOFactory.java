package no.citrus.restapi;

public class MongoDAOFactory extends DAOFactory {
	public MeasureDAO getMeasureDAO() {
		return new MongoMeasureDAO();
	}
}