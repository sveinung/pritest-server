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
import no.pritest.restapi.model.Author;
import no.pritest.restapi.model.Change;
import no.pritest.restapi.model.Commit;
import no.pritest.restapi.model.Repository;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
	public boolean insert(Change value) {
		DB db;
		try {
			db = MongoDBProvider.getInstance().getDB();
			DBCollection coll = db.getCollection("change");
			
			BasicDBObject doc = new BasicDBObject();
			doc.put("after", value.getAfter());
			doc.put("before", value.getBefore());
			
			List<Commit> commits = value.getCommits();
			ArrayList<BasicDBObject> commitDocs = new ArrayList<BasicDBObject>();
			for(Commit c : commits) {
				BasicDBObject commitDoc = new BasicDBObject();
				
				commitDoc.put("added", c.getAdded());
				commitDoc.put("id", c.getId());
				commitDoc.put("message", c.getMessage());
				commitDoc.put("modified", c.getModified());
				commitDoc.put("removed", c.getRemoved());
				commitDoc.put("url", c.getUrl());
				commitDoc.put("timestamp", c.getTimestamp());
				
				Author author = c.getAuthor();
				BasicDBObject authorDoc = new BasicDBObject();
				authorDoc.put("email", author.getEmail());
				authorDoc.put("name", author.getName());
				
				commitDoc.put("author", authorDoc);
				commitDocs.add(commitDoc);
			}
			doc.put("commits", commitDocs);
			
			doc.put("ref", value.getRef());
			doc.put("compare", value.getCompare());
			doc.put("forced", value.isForced());
			
			BasicDBObject pusherDoc = new BasicDBObject();
			pusherDoc.put("email", value.getPusher().getEmail());
			pusherDoc.put("name", value.getPusher().getName());
			doc.put("pusher", pusherDoc);
			
			BasicDBObject reposDoc = new BasicDBObject();
			Repository rep = value.getRepository();
			reposDoc.put("url", rep.getUrl());
			reposDoc.put("name", rep.getName());
			reposDoc.put("description", rep.getDescription());
			reposDoc.put("watchers", rep.getWatchers());
			reposDoc.put("forks", rep.getForks());
			reposDoc.put("private", rep.isPriv());
			reposDoc.put("created_at", rep.getCreatedAt());
			reposDoc.put("fork", rep.isFork());
			reposDoc.put("has_downloads", rep.isHasDownloads());
			reposDoc.put("has_issues", rep.isHasIssues());
			reposDoc.put("has_wiki", rep.isHasWiki());
			reposDoc.put("homepage", rep.getHomepage());
			reposDoc.put("pushed_at", rep.getPushedAt());
			reposDoc.put("open_issues", rep.getOpenIssues());
			
			BasicDBObject ownerDoc = new BasicDBObject();
			ownerDoc.put("email", rep.getOwner().getEmail());
			ownerDoc.put("name", rep.getOwner().getName());
			
			reposDoc.put("owner", ownerDoc);
			
			doc.put("repository", reposDoc);
			
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
