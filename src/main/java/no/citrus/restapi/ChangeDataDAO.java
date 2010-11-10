package no.citrus.restapi;

import java.util.List;

import no.citrus.restapi.model.ChangeData;

public interface ChangeDataDAO {
	public ChangeData get(String source);
	public List<ChangeData> getList();
	public void update(ChangeData changeData);
}
