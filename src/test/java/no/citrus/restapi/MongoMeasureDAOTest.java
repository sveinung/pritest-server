package no.citrus.restapi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import no.citrus.restapi.configuration.Configuration;
import no.citrus.restapi.configuration.PropertiesHolder;
import no.citrus.restapi.model.Measure;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.MongoException;



public class MongoMeasureDAOTest {
	@Ignore
	@Test
	public void shouldInsertAndRetrieveMeasure() {
		Measure m = new Measure("test");
		MeasureDAO measureDAO = new MongoMeasureDAO();
		measureDAO.insert(m);
		Measure result = measureDAO.get("test");
		
		assertThat(m.getName(), equalTo(result.getName()));
	}
	@Ignore
	@Test
	public void valueShouldBeDeleted() throws MongoException, UnknownHostException{
		Measure m = new Measure("test");
		MeasureDAO measureDAO = new MongoMeasureDAO();
		measureDAO.insert(m);
		measureDAO.delete("test");
		
		assertThat(measureDAO.get("Test"), nullValue());
	}
	@Test
	public void shouldConnectToServer() throws UnknownHostException, IOException {
		Configuration config = new Configuration(PropertiesHolder.getInstance());
		new Socket(config.getDatabaseURL(), config.getDatabasePort());
	}
	@After
	public void after() throws MongoException, UnknownHostException {
		MeasureDAO measureDAO = new MongoMeasureDAO();
		measureDAO.delete("Test");
	}
}
