package restapi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Test;

import com.mongodb.MongoException;

import restapi.model.Measure;


public class MongoMeasureDAOTest {
	@Test
	public void shouldInsertAndRetrieveMeasure() {
		Measure m = new Measure("test");
		MeasureDAO measureDAO = new MongoMeasureDAO();
		measureDAO.insert("Test", m);
		Measure result = measureDAO.get("Test");
		
		assertThat(m.getName(), equalTo(result.getName()));
	}
	@Test
	public void valueShouldBeDeleted() throws MongoException, UnknownHostException{
		Measure m = new Measure("test");
		MeasureDAO measureDAO = new MongoMeasureDAO();
		measureDAO.insert("Test", m);
		measureDAO.delete("Test");
		
		assertThat(measureDAO.get("Test"), nullValue());
	}
	@Test
	public void shouldConnectToServer() throws UnknownHostException, IOException {
		new Socket(Global.DB_URL, Global.DB_PORT);
	}
	@After
	public void after() throws MongoException, UnknownHostException {
		MeasureDAO measureDAO = new MongoMeasureDAO();
		measureDAO.delete("Test");
	}
}
