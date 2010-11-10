package no.citrus.restapi.model;

import java.util.Date;

public class ChangeData implements Comparable<ChangeData> {
	private String source;
	private Date lastChange;
	
	public ChangeData(String source, Date lastChange) {
		super();
		this.source = source;
		this.lastChange = lastChange;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getLastChange() {
		return lastChange;
	}
	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}

	@Override
	public int compareTo(ChangeData arg) {
		return lastChange.compareTo(arg.getLastChange());
	}
}
