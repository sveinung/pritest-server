package no.citrus.restapi;

import java.net.UnknownHostException;

import restapi.model.Measure;

import com.mongodb.MongoException;


public interface MeasureDAO {
	public Measure get(String key);
	public boolean insert(String key, Measure value);
	public void delete(String key) throws MongoException, UnknownHostException;
}