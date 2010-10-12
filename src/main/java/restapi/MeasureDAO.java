package restapi;

import java.net.UnknownHostException;

import com.mongodb.MongoException;

import restapi.model.Measure;

public interface MeasureDAO {
	public Measure get(String key);
	public boolean insert(String key, Measure value);
	public void delete(String key) throws MongoException, UnknownHostException;
}
