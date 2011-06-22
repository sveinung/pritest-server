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

import com.sun.jersey.api.json.JSONJAXBContext;
import com.sun.jersey.api.json.JSONUnmarshaller;
import no.pritest.restapi.ChangeDAO;
import no.pritest.restapi.ChangeDataDAO;
import no.pritest.restapi.DAOFactory;
import no.pritest.restapi.model.ChangeData;
import no.pritest.restapi.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.util.Date;

@Path("/change")
public class ChangeResource {
	
	@Path("/{after}")
    @GET
    @Produces({"application/xml", "application/json"})
    public Change get(@PathParam("after") String after) {
    	ChangeDAO dao = DAOFactory.getDatabase().getChangeDAO();
    	return dao.get(after);
    }

    @POST
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
    public Response post(@FormParam("payload") String changeString) {
    	try {
			JSONJAXBContext context = new JSONJAXBContext(Author.class, Change.class,
					Commit.class, Owner.class, Pusher.class, Repository.class);
			JSONUnmarshaller unmarshaller = context.createJSONUnmarshaller();
			Change change = unmarshaller.unmarshalFromJSON(new StringReader(changeString), Change.class);
			
			ChangeDAO changeDAO = DAOFactory.getDatabase().getChangeDAO();
			changeDAO.insert(change);
	    	
	    	for (Commit commit : change.getCommits()) {
	    		Date timestamp = commit.getTimestamp();
	    		for (String added : commit.getAdded()) {
	    			updateChangeData(timestamp, added);
	    		}
	    		for (String modified : commit.getModified()) {
	    			updateChangeData(timestamp, modified);
	    		}
	    		for (String removed : commit.getRemoved()) {
	    			updateChangeData(timestamp, removed);
	    		}
	    	}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return Response.ok().build();
    }

	private void updateChangeData(Date timestamp, String source) {
		if (source.equals("")) {
			return;
		}
		ChangeDataDAO cdDAO = DAOFactory.getDatabase().getChangeDataDAO();
		ChangeData changeData = cdDAO.get(source);
		if (changeData != null) {
			changeData.setLastChange(timestamp);
		} else {
			changeData = new ChangeData(source, timestamp);
		}
		cdDAO.update(changeData);
	}
}