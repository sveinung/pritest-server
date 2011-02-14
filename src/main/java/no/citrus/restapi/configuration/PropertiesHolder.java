package no.citrus.restapi.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertiesHolder implements Configurable {

	private static PropertiesHolder instance;
	private Properties properties;
	
	private PropertiesHolder(){
		try {
			FileInputStream inStream = new FileInputStream("./citrus.properties");
			properties = new Properties();
			properties.load(inStream);
			inStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Map<Object, Object> getProperties() {
		return properties;
	}
	
	public static PropertiesHolder getInstance(){
		if(instance == null){
			instance = new PropertiesHolder();
		}
		return instance;
	}
}
