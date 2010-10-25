package no.citrus.restapi;

import java.net.UnknownHostException;

import no.citrus.restapi.model.Change;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

public class MongoChangeDAO implements ChangeDAO {

	@Override
	public void delete(String after) throws MongoException, UnknownHostException {
		DB db = MongoDBProvider.getInstance().getDB();
		DBCollection coll = db.getCollection("change");
		
		BasicDBObject removalQuery = new BasicDBObject();
		removalQuery.put("after", after);
		
		coll.remove(removalQuery);
	}

	@Override
	public Change get(String after) {
		DB db = null;
		try {
			db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("change");
			
			BasicDBObject query = new BasicDBObject();
			query.put("after", after);
			DBCursor cursor = coll.find(query);
			
			if (cursor.hasNext()) {
				DBObject result = cursor.next();
				Change change = new Change();
				change.setAfter((String)result.get("after"));
				change.setBefore((String)result.get("before"));
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
//			doc.put("key", key);
			doc.put("after", value.getAfter());
			doc.put("before", value.getBefore());
			
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
