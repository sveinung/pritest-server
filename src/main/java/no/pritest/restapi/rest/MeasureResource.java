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

package no.pritest.restapi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import no.pritest.restapi.DAOFactory;
import no.pritest.restapi.MeasureDAO;
import no.pritest.restapi.TestDataDAO;
import no.pritest.restapi.model.TestData;
import no.pritest.restapi.model.Measure;
import no.pritest.restapi.model.MeasureList;

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
