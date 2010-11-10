package no.citrus.restapi.rest;

import java.io.StringReader;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import no.citrus.restapi.ChangeDAO;
import no.citrus.restapi.ChangeDataDAO;
import no.citrus.restapi.DAOFactory;
import no.citrus.restapi.model.Author;
import no.citrus.restapi.model.Change;
import no.citrus.restapi.model.ChangeData;
import no.citrus.restapi.model.Commit;
import no.citrus.restapi.model.Owner;
import no.citrus.restapi.model.Pusher;
import no.citrus.restapi.model.Repository;

import com.sun.jersey.api.json.JSONJAXBContext;
import com.sun.jersey.api.json.JSONUnmarshaller;

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