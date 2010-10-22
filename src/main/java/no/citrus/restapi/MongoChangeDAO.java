package no.citrus.restapi;

import java.net.UnknownHostException;

import no.citrus.restapi.model.Change;
import no.citrus.restapi.model.Measure;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

public class MongoChangeDAO implements ChangeDAO {

	@Override
	public void delete(String key) throws MongoException, UnknownHostException {
		DB db = MongoDBProvider.getInstance().getDB();
		DBCollection coll = db.getCollection("change");
		
		BasicDBObject removalQuery = new BasicDBObject();
		removalQuery.put("key", key);
		
		coll.remove(removalQuery);
	}

	@Override
	public Change get(String key) {
		DB db = null;
		try {
			db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("change");
			
			BasicDBObject query = new BasicDBObject();
			query.put("key", key);
			DBCursor cursor = coll.find(query);
			
			if (cursor.hasNext()) {
				DBObject result = cursor.next();
				Change change = new Change((String)result.get("name"));
				return change;
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
	public boolean insert(String key, Change value) {
		DB db;
		try {
			db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("change");
			
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
