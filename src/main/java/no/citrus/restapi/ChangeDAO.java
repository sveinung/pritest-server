package no.citrus.restapi;

import java.net.UnknownHostException;

import com.mongodb.MongoException;

import no.citrus.restapi.model.Change;

public interface ChangeDAO {
	public Change get(String key);
	public boolean insert(String key, Change value);
	public void delete(String key) throws MongoException, UnknownHostException;
}
