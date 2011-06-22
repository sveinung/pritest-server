/**
    This file is part of Pritest.

    Pritest is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Pritest is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package no.citrus.restapi;

import com.mongodb.*;
import no.pritest.restapi.model.Measure;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
