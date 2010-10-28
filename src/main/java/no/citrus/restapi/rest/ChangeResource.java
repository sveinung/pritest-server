package no.citrus.restapi.rest;

import java.io.StringReader;

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
import no.citrus.restapi.DAOFactory;
import no.citrus.restapi.model.Author;
import no.citrus.restapi.model.Change;
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
			
			ChangeDAO dao = DAOFactory.getDatabase().getChangeDAO();
	    	dao.insert(change);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return Response.ok().build();
    }
}