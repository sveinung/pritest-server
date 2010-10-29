package no.citrus.restapi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import no.citrus.restapi.DAOFactory;
import no.citrus.restapi.MeasureDAO;
import no.citrus.restapi.model.Measure;


@Path("/measure")
public class MeasureResource {
    @POST
    @Consumes({"application/xml","application/json"})
    public Response post(Measure measure) {
    	MeasureDAO dao = DAOFactory.getDatabase().getMeasureDAO();
    	dao.insert(measure.getName(), measure);
        return Response.ok().build();
    }
}
