package substrate.tracker.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projects")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ProjectsResource {
    @GET
    public Response getProjects(
            @QueryParam("limit") @DefaultValue("10") final Integer limit,
            @QueryParam("offset") @DefaultValue("0") final Integer offset
    ) {
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
