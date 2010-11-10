package no.citrus.restapi;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.citrus.restapi.model.ChangeData;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class MongoChangeDataDAO implements ChangeDataDAO {

	@Override
	public ChangeData get(String source) {
		try {
			DB db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("changedata");
			
			BasicDBObject query = new BasicDBObject();
			query.put("source", source);
			DBCursor cursor = coll.find(query);
			
			if (cursor.hasNext()) {
				DBObject result = cursor.next();
				ChangeData changeData = new ChangeData((String) result.get("name"), (Date) result.get("lastChange"));
				return changeData;
			}
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ChangeData> getList() {
		try {
			DB db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("changedata");
			List<ChangeData> results = new ArrayList<ChangeData>();
			
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				ChangeData changeData = new ChangeData((String) result.get("source"), (Date) result.get("lastChange"));
				results.add(changeData);
			}
			
			return results;
			
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(ChangeData changeData) {
		try {
			DB db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("changedata");
			
			BasicDBObject query = new BasicDBObject();
			query.put("source", changeData.getSource());
			
			BasicDBObject object = new BasicDBObject();
			object.put("source", changeData.getSource());
			object.put("lastChange", changeData.getLastChange());
			
			coll.update(query, object, true, false);
			
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
