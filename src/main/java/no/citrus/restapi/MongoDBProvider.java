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

import java.net.UnknownHostException;

import no.citrus.restapi.configuration.Configuration;
import no.citrus.restapi.configuration.PropertiesHolder;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDBProvider {
	private static MongoDBProvider INSTANCE;
	private DB db = null;
	
	private MongoDBProvider() throws MongoException, UnknownHostException {
		Configuration config = new Configuration(PropertiesHolder.getInstance());
		Mongo mongo = new Mongo(config.getDatabaseURL(), config.getDatabasePort());
		db = mongo.getDB(config.getDatabaseName());
	}
	
	public static MongoDBProvider getInstance() throws MongoException, UnknownHostException {
		if (INSTANCE == null) {
			INSTANCE = new MongoDBProvider();
		}
		return INSTANCE;
	}
	
	public DB getDB() {
		return db;
	}
}
