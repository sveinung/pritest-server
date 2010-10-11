package restapi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import restapi.model.Measure;

public class DatabaseHandlerTest {
	
	@Test
	public void shouldSaveAndGetData() {
		Measure savedValue = new Measure("Test");
		DatabaseHandler.save("test", savedValue);
		Measure measure = DatabaseHandler.get("test");
		
		assertThat(savedValue, equalTo(measure));
	}
}
