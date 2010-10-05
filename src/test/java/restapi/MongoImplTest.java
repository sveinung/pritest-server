package restapi;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MongoImplTest {
	
	static MongoImpl mongoImpl;
	
	@BeforeClass
	public static void before() throws Exception { 
		mongoImpl = new MongoImpl("localhost", 27017, "citrus");
	}
	
	@Test
	public void testDatabaseName() {
		assertThat(mongoImpl.getDBName(), equalTo("citrus"));
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
