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

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.MongoException;
import no.pritest.restapi.model.Measure;


public interface MeasureDAO {
	public Measure get(String key);
	public boolean insert(Measure value);
	public void delete(String key) throws MongoException, UnknownHostException;
	public List<Measure> getList();
}
