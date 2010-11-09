package no.citrus.restapi.model;

public class TestData {
	private String className;
	private int fails;
	
	public TestData(String className, int fails) {
		this.className = className;
		this.fails = fails;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String name) {
		this.className = name;
	}
	
	public int getFails() {
		return fails;
	}
	public void setFails(int fails) {
		this.fails = fails;
	}
	
	
}
