/**
    This file is part of Pritest.

    Pritest is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Pritest is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package no.pritest.restapi.configuration;

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
			FileInputStream inStream = new FileInputStream("./pritest.properties");
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
