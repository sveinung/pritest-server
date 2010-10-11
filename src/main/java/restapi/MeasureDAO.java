package restapi;

import restapi.model.Measure;

public interface MeasureDAO {
	public Measure get(String key);
	public boolean insert(String key, Measure value);
}
