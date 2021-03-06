package substrate.tracker.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ping")
@Produces(MediaType.TEXT_PLAIN)
public class PingResource {
    @GET
    public Response ping() {
        return Response.ok("I'm awake!").build();
    }
}
