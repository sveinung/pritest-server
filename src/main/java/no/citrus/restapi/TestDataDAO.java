package no.citrus.restapi;

import java.net.UnknownHostException;

import com.mongodb.MongoException;

import no.citrus.restapi.model.TestData;

public interface TestDataDAO {
	public TestData get(String className);
	public void update(TestData testData);
	public void delete(String className) throws MongoException, UnknownHostException;
}
