package restapi;

import java.net.UnknownHostException;

import restapi.model.Measure;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

public class MongoMeasureDAO implements MeasureDAO {
	public MongoMeasureDAO() {
	}

	@Override
	public Measure get(String key) {
		return null;
	}
	
	@Override
	public boolean insert(String key, Measure value) {
		DB db;
		try {
			db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("measure");
			
			BasicDBObject doc = new BasicDBObject();
			doc.put("key", key);
			doc.put("name", value.getName());
			
			WriteResult wr = coll.insert(doc);
			
			return wr.getLastError().ok();
			
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
