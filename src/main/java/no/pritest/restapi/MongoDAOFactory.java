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

public class MongoDAOFactory extends DAOFactory {
	public MeasureDAO getMeasureDAO() {
		return new MongoMeasureDAO();
	}

	@Override
	public ChangeDAO getChangeDAO() {
		return new MongoChangeDAO();
	}

	@Override
	public TestDataDAO getTestDataDAO() {
		return new MongoTestDataDAO();
	}

	@Override
	public ChangeDataDAO getChangeDataDAO() {
		return new MongoChangeDataDAO();
	}
	
	
}
