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
			
			String processedSource = processSource(changeData.getSource());
			
			BasicDBObject query = new BasicDBObject();
			query.put("source", processedSource);
			
			BasicDBObject object = new BasicDBObject();
			object.put("source", processedSource);
			object.put("lastChange", changeData.getLastChange());
			
			coll.update(query, object, true, false);
			
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	private String processSource(String source) {
		String[] result = source.split("/");
		String packagePath = "";
		for (int i=3; i < result.length; i++) {
			System.out.println("result[i] " + result[i]);
			if (result[i].contains("Test.java")) {
				packagePath += result[i].substring(0, result[i].length() - 5);
			}
			else if (result[i].contains(".java")) {
				packagePath += result[i].substring(0, result[i].length() - 5) + "Test";
			} else {
				packagePath += result[i] + ".";
			}
		}
		System.out.println(packagePath);
		return packagePath;
	}
}
