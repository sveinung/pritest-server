package no.citrus.restapi;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.UnknownHostException;

import no.citrus.restapi.MongoDBProvider;

import org.junit.Test;


import com.mongodb.DB;
import com.mongodb.MongoException;


public class MongoDBProviderTest {
	@Test
	public void shouldReturnDBObject() throws MongoException, UnknownHostException {
		DB db = MongoDBProvider.getInstance().getDB();
		assertThat(db, not(equalTo(null)));
	}
	
	@Test
	public void dbStatsShouldBeOk() throws MongoException, UnknownHostException {
		DB db = MongoDBProvider.getInstance().getDB();
		assertThat(db.getStats().ok(), equalTo(true));
	}
}
