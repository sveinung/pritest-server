package restapi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import restapi.model.Measure;


public class MongoMeasureDAOTest {
	@Test
	public void shouldInsertMeasure() {
		Measure m = new Measure("test");
		MeasureDAO measureDAO = new MongoMeasureDAO();
		boolean result = measureDAO.insert("Test", m);
		assertThat(result, equalTo(true));
	}
}
