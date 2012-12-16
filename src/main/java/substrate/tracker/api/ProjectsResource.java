package substrate.tracker.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projects")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class ProjectsResource {
    @GET
    public Response getProjects(
            @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        return Response.ok().build();
    }
    
    @POST
    public Response createProject() {
        return Response.ok().build();
    }
    
    @GET
    @Path("/{projectId}")
    public Response getProject(@PathParam("projectId") final Long projectId) {
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{projectId}")
    public Response deleteProject(@PathParam("projectId") final Long projectId) {
        return Response.ok().build();
    }
    
    @PUT
    @Path("/{projectId}")
    public Response updateProject(@PathParam("projectId") final Long projectId) {
        return Response.ok().build();
    }
}
