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

package no.citrus.restapi.rest;

import no.citrus.restapi.ChangeDataDAO;
import no.citrus.restapi.DAOFactory;
import no.citrus.restapi.MeasureDAO;
import no.citrus.restapi.TestDataDAO;
import no.citrus.restapi.model.ChangeData;
import no.citrus.restapi.model.TestData;
import no.pritest.restapi.model.Measure;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.*;

@Path("/testorder")
public class TestOrderResource {

	@Path("/{method}")
	@GET
	@Produces("application/json")
	public List<String> get(@PathParam("method") int method) {
		List<String> testClasses = new ArrayList<String>();
		
		switch (method) {
		case 1:
			testClasses = method1();
			break;
		case 2:
			testClasses = method2();
			break;
		case 3:
			testClasses = method3();
			break;
		case 5:
			//Technique 5 is a hybrid of method1 and git status.
			testClasses = method1();
			break;
		}
		
		return testClasses;
	}

	private List<String> method1() {
		TestDataDAO tdDAO = DAOFactory.getDatabase().getTestDataDAO();
		List<TestData> tests = tdDAO.getList();
		Collections.sort(tests);
		
		List<String> testNames = new ArrayList<String>();
		for (TestData test : tests) {
			testNames.add(test.getClassName());
		}
		
		return testNames;
	}
	
	private List<String> method2() {
		MeasureDAO mDAO = DAOFactory.getDatabase().getMeasureDAO();
		List<Measure> measures = mDAO.getList();
		Collections.sort(measures);
		
		List<String> measureNamesFiltered = new ArrayList<String>();
		for (Measure m : measures) {
			
			Calendar threeDaysAgo = Calendar.getInstance();
			threeDaysAgo.add(Calendar.DATE, -3);
			Date threeDaysAgoDate = new Date(threeDaysAgo.getTimeInMillis());
			
			if (m.getDate().after(threeDaysAgoDate)) {
				if (!measureNamesFiltered.contains(m.getSource())) {
					measureNamesFiltered.add(m.getSource());	
				}
			}
		}
		
		return measureNamesFiltered;
	}
	
	private List<String> method3() {
		ChangeDataDAO cdDAO = DAOFactory.getDatabase().getChangeDataDAO();
		List<ChangeData> changes = cdDAO.getList();
		Collections.sort(changes);
		
		List<String> testNames = new ArrayList<String>();
		for (ChangeData change : changes) {
			testNames.add(change.getSource());
			System.out.println(change.getSource());
		}
		
		return testNames;
	}
}
