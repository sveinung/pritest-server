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

import no.pritest.restapi.configuration.Configuration;
import no.pritest.restapi.configuration.PropertiesHolder;


public abstract class DAOFactory {
	
	public abstract MeasureDAO getMeasureDAO();
	
	public abstract ChangeDAO getChangeDAO();
	
	public abstract TestDataDAO getTestDataDAO();
	
	public abstract ChangeDataDAO getChangeDataDAO();
	
	public static DAOFactory getDatabase() {
		Configuration config = new Configuration(PropertiesHolder.getInstance());
		switch (config.getDatabaseType()) {
		case Mongo:
			return new MongoDAOFactory();
		default:
			return null;
		}
	}
}