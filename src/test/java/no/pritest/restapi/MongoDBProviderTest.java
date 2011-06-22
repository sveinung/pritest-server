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

package no.pritest.restapi;

import com.mongodb.DB;
import com.mongodb.MongoException;
import org.junit.Ignore;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MongoDBProviderTest {
	@Test
	public void should_return_db_object() throws MongoException, UnknownHostException {
		DB db = MongoDBProvider.getInstance().getDB();
		assertThat(db, not(equalTo(null)));
	}
	@Ignore
	@Test
	public void db_stats_should_be_ok() throws MongoException, UnknownHostException {
		DB db = MongoDBProvider.getInstance().getDB();
		assertThat(db.getStats().ok(), equalTo(true));
	}
}
