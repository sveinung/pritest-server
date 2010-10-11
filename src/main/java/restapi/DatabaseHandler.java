package restapi;

import java.util.HashMap;

import restapi.model.Measure;

public class DatabaseHandler {
	
	private static DatabaseHandler INSTANCE;
//	private Database db;
	
	private DatabaseHandler() {
//		db = DAOFactory.getDatabase();
	}
	
	public static DatabaseHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DatabaseHandler();
		}
		return INSTANCE;
	}
	
    private static HashMap<String, Measure> map = new HashMap<String, Measure>() {
        {
            put("one", new Measure("one"));
        }
    };

    public void saveValue(String key, Measure value) {
        map.put(key, value);
    }

    public Measure getValue(String key) {
        return map.get(key);
    }
    
    public static void save(String key, Measure value) {
    	getInstance().saveValue(key, value);
    }
    
    public static Measure get(String key) {
        return getInstance().getValue(key);
    }
}
