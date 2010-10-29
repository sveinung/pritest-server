package no.citrus.restapi.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/testorder")
public class TestOrderResource {

	@Path("/{method}")
	@GET
	@Produces("application/json")
	public List<String> get(@PathParam("method") String method) {
		return new ArrayList<String>();
	}
}
