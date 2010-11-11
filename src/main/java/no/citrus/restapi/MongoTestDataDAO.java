package no.citrus.restapi;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import no.citrus.restapi.model.TestData;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class MongoTestDataDAO implements TestDataDAO {
	
	@Override
	public TestData get(String className) {
		DB db = null;
		try {
			db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("testdata");
			
			BasicDBObject query = new BasicDBObject();
			query.put("source", className);
			DBCursor cursor = coll.find(query);
			
			if (cursor.hasNext()) {
				DBObject result = cursor.next();
				TestData testData = new TestData((String) result.get("source"), (Integer) result.get("fails"));
				return testData;
			}
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return new TestData(className, 0);
	}

	@Override
	public void update(TestData testData) {
		try {
			DB db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("testdata");
			
			BasicDBObject query = new BasicDBObject();
			query.put("source", testData.getClassName());
			
			BasicDBObject object = new BasicDBObject();
			object.put("source", testData.getClassName());
			object.put("fails", testData.getFails());
			
			coll.update(query, object, true, false);
			
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String className) throws MongoException, UnknownHostException {
		DB db = MongoDBProvider.getInstance().getDB();
		DBCollection coll = db.getCollection("testdata");
		
		BasicDBObject removalQuery = new BasicDBObject();
		removalQuery.put("source", className);
		
		coll.remove(removalQuery);
	}

	@Override
	public List<TestData> getList() {
		try {
			DB db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("testdata");
			List<TestData> results = new ArrayList<TestData>();
			
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				TestData testData = new TestData((String) result.get("source"), (Integer) result.get("fails"));
				results.add(testData);
			}
			
			return results;
			
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
}
