package restapi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MongoImplTest {
	
	static MongoImpl mongoImpl;
	
	@BeforeClass
	public static void before() throws Exception { 
		mongoImpl = new MongoImpl("localhost", 27017, "citrus_test");
	}
	
	@Test
	public void testDatabaseName() {
		assertThat(mongoImpl.getDBName(), equalTo("citrus_test"));
	}
	
	@Test
	public void testHost() {
		assertThat(mongoImpl.getHost(), equalTo("localhost"));
	}
	
	@Test
	public void testPort() {
		assertThat(mongoImpl.getPort(), equalTo(27017));
	}
}
