package no.citrus.restapi;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.citrus.restapi.model.Commit;
import no.citrus.restapi.model.Measure;
import no.citrus.restapi.model.TestData;

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
			
			WriteResult wr = null;
			BasicDBObject doc = new BasicDBObject();
			doc.put("source", value.getSource());
			doc.put("date", value.getDate());
			
			int failCount = 0;
			ArrayList<BasicDBObject> childrenDocs = new ArrayList<BasicDBObject>();
			
			for (Measure child : value.getChildren()) {
				
				if (child.isFailed()) {
					failCount++;
				}
				
				BasicDBObject childDoc = new BasicDBObject();
				childDoc.put("failed", child.isFailed());
				childrenDocs.add(childDoc);
			}
			
			doc.put("children", childrenDocs);
			doc.put("numOfFails", failCount);
			
			wr = coll.insert(doc);
			return wr.getLastError().ok();
			
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
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
	
	@Override
	public List<Measure> getList() {
		List<Measure> listOfMeasures = new ArrayList<Measure>();
		
		DB db = null;
		try {
			db = MongoDBProvider.getInstance().getDB();
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
		DBCollection coll = db.getCollection("measure");
		
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject result = cursor.next();
			Measure measure = new Measure((String) result.get("source"), (Date) result.get("date"), (List) result.get("children"), (Integer) result.get("numOfFails"));
			listOfMeasures.add(measure);
		}
		
		return listOfMeasures;
	}
}
