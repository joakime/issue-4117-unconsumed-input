package test.jetty.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class TestEndpoint {

    @Inject
    private WebTarget api;

    @GET
    @Path("/test")
    public Response test() {
        List<String> params = new ArrayList<>();
        for (int i= 0; i < 10; i++) {
            params.add("ARR" + i);
        }
        try (Response response = api.path("v1")
                .path("test")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(params))) {
            return Response.ok(response.readEntity(new GenericType<List<String>>() {})).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
