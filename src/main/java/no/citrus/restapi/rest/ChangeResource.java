package no.citrus.restapi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import no.citrus.restapi.ChangeDAO;
import no.citrus.restapi.DAOFactory;
import no.citrus.restapi.model.Change;

@Path("/change")
public class ChangeResource {
	
	@Path("/{name}")
    @GET
    @Produces({"application/xml", "application/json"})
    public Change get(@PathParam("name") String name) {
    	ChangeDAO dao = DAOFactory.getDatabase().getChangeDAO();
    	return dao.get(name);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(Change change) {
    	ChangeDAO dao = DAOFactory.getDatabase().getChangeDAO();
    	dao.insert(change.getName(), change);
        return Response.ok().build();
    }

}
