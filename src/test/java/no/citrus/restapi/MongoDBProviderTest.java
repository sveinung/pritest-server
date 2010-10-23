package no.citrus.restapi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.net.UnknownHostException;

import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.MongoException;


public class MongoDBProviderTest {
	@Test
	public void shouldReturnDBObject() throws MongoException, UnknownHostException {
		DB db = MongoDBProvider.getInstance().getDB();
		assertThat(db, not(equalTo(null)));
	}
	@Ignore
	@Test
	public void dbStatsShouldBeOk() throws MongoException, UnknownHostException {
		DB db = MongoDBProvider.getInstance().getDB();
		assertThat(db.getStats().ok(), equalTo(true));
	}
}
