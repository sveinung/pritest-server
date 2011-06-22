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

package no.pritest.restapi;

import com.mongodb.*;
import no.pritest.restapi.model.TestData;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
