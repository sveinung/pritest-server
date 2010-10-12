package restapi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import restapi.DAOFactory;
import restapi.MeasureDAO;
import restapi.model.Measure;


@Path("/measure")
public class MeasureResource {

    @Path("/{name}")
    @GET
    @Produces({"application/xml", "application/json"})
    public Measure get(@PathParam("name") String name) {
    	MeasureDAO dao = DAOFactory.getDatabase().getMeasureDAO();
    	return dao.get(name);
    }

    @POST
    @Consumes({"application/xml","application/json"})
    public Response post(Measure measure) {
    	MeasureDAO dao = DAOFactory.getDatabase().getMeasureDAO();
    	dao.insert(measure.getName(), measure);
//        DatabaseHandler.save(measure.getName(), measure);
        return Response.ok().build();
    }
}
