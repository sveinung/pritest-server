package no.citrus.restapi;

import java.net.UnknownHostException;
import java.util.List;

import no.citrus.restapi.model.TestData;

import com.mongodb.MongoException;

public interface TestDataDAO {
	public TestData get(String className);
	public List<TestData> getList();
	public void update(TestData testData);
	public void delete(String className) throws MongoException, UnknownHostException;
}
