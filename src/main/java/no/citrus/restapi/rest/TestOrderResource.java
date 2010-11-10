package no.citrus.restapi.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import no.citrus.restapi.ChangeDataDAO;
import no.citrus.restapi.DAOFactory;
import no.citrus.restapi.TestDataDAO;
import no.citrus.restapi.model.ChangeData;
import no.citrus.restapi.model.TestData;

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
		return null;
	}
	
	private List<String> method3() {
		ChangeDataDAO cdDAO = DAOFactory.getDatabase().getChangeDataDAO();
		List<ChangeData> changes = cdDAO.getList();
		Collections.sort(changes);
		
		List<String> testNames = new ArrayList<String>();
		for (ChangeData change : changes) {
			testNames.add(change.getSource());
		}
		
		return testNames;
	}
}
