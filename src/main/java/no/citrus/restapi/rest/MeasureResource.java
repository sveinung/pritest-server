package no.citrus.restapi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import no.citrus.restapi.DAOFactory;
import no.citrus.restapi.MeasureDAO;
import no.citrus.restapi.TestDataDAO;
import no.citrus.restapi.model.Measure;
import no.citrus.restapi.model.MeasureList;
import no.citrus.restapi.model.TestData;


@Path("/measure")
public class MeasureResource {
    @POST
    @Consumes({"application/xml"})
    public Response post(MeasureList measures) {
    	MeasureDAO mdao = DAOFactory.getDatabase().getMeasureDAO();
    	TestDataDAO tdao = DAOFactory.getDatabase().getTestDataDAO();
    	
    	for (Measure measure : measures.getList()) {
	    	mdao.insert(measure);
	    	
	    	int numberOfFails = 0;
	    	for (Measure child : measure.getChildren()) {
	    		if (child.isFailed()) {
	    			numberOfFails++;
	    		}
	    	}
	    	
	    	TestData testData = tdao.get(measure.getSource());
	    	testData.setFails(testData.getFails() + numberOfFails);
	    	tdao.update(testData);
    	}
    	
        return Response.ok().build();
    }
}
