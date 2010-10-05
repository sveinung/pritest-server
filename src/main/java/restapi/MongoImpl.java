package restapi;

import java.net.UnknownHostException;
import java.util.HashMap;

import restapi.model.Measure;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoImpl {
	private Mongo mongo;
	private DB db;
	
	public MongoImpl(String host, int port, String name) {
		try {
			mongo = new Mongo(host, port);
			db = mongo.getDB(name);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    private static HashMap<String, Measure> map = new HashMap<String, Measure>() {
        {
            put("one", new Measure("one"));
        }
    };

    public static void save(String key, Measure value) {
        map.put(key, value);
    }

    public static Measure get(String key) {
        return map.get(key);
    }
    
    public String getDBName() {
    	return db.getName();
    }
    
    public String getHost() {
    	return mongo.getAddress().getHost();
    }
    
    public int getPort() {
    	return mongo.getAddress().getPort();
    }
}
