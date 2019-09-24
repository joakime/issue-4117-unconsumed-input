package test.jetty.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/v1")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class TestEndpoint {

    @POST
    @Path("/test")
    public Response test(List<String> input) {
        return Response.ok(Collections.emptyList()).build();
    }
}
