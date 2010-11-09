package no.citrus.restapi;

import java.net.UnknownHostException;

import no.citrus.restapi.model.Measure;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

public class MongoMeasureDAO implements MeasureDAO {
	
	@Override
	public Measure get(String key) {
		DB db = null;
		try {
			db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("measure");
			
			BasicDBObject query = new BasicDBObject();
			query.put("name", key);
			
			DBCursor cursor = coll.find(query);
			
			if (cursor.hasNext()) {
				DBObject result = cursor.next();
				Measure measure = new Measure((String)result.get("name"));
				return measure;
			}
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean insert(Measure value) {
		DB db;
		try {
			db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("measure");
			
			BasicDBObject doc = new BasicDBObject();
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
	
	@Override
	public void delete(String name) throws MongoException, UnknownHostException {
		DB db = MongoDBProvider.getInstance().getDB();
		DBCollection coll = db.getCollection("measure");
		
		BasicDBObject removalQuery = new BasicDBObject();
		removalQuery.put("name", name);
		
		coll.remove(removalQuery);
	}
}
