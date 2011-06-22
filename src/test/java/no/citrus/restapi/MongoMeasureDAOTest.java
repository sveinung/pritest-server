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

package no.citrus.restapi;

import com.mongodb.MongoException;
import no.citrus.restapi.configuration.Configuration;
import no.citrus.restapi.configuration.PropertiesHolder;
import no.pritest.restapi.model.Measure;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class MongoMeasureDAOTest {
	@Ignore
	@Test
	public void should_insert_and_retrieve_measure() {
		Measure m = new Measure("test");
		MeasureDAO measureDAO = new MongoMeasureDAO();
		measureDAO.insert(m);
		Measure result = measureDAO.get("test");
		
		assertThat(m.getName(), equalTo(result.getName()));
	}
	@Ignore
	@Test
	public void value_should_be_deleted() throws MongoException, UnknownHostException{
		Measure m = new Measure("test");
		MeasureDAO measureDAO = new MongoMeasureDAO();
		measureDAO.insert(m);
		measureDAO.delete("test");
		
		assertThat(measureDAO.get("Test"), nullValue());
	}
	@Test
	public void should_connect_to_server() throws UnknownHostException, IOException {
		Configuration config = new Configuration(PropertiesHolder.getInstance());
		new Socket(config.getDatabaseURL(), config.getDatabasePort());
	}
	@After
	public void after() throws MongoException, UnknownHostException {
		MeasureDAO measureDAO = new MongoMeasureDAO();
		measureDAO.delete("Test");
	}
}
