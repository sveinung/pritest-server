package no.citrus.restapi;

import java.net.UnknownHostException;
import java.util.List;

import no.citrus.restapi.model.Measure;

import com.mongodb.MongoException;


public interface MeasureDAO {
	public Measure get(String key);
	public boolean insert(Measure value);
	public void delete(String key) throws MongoException, UnknownHostException;
	public List<Measure> getList();
}
