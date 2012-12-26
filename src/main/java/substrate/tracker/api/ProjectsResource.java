package substrate.tracker.api;

import substrate.tracker.dto.ProjectDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projects")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ProjectsResource {
    @GET
    public Response getProjects(
            @QueryParam("limit") final int limit,
            @QueryParam("offset") final int offset
    ) {
        if (limit < 0 || offset < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }

    @POST
    public Response createProject(final ProjectDTO projectDto) {
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
    public Response updateProject(
            @PathParam("projectId") final Long projectId,
            final ProjectDTO projectDto
    ) {
        return Response.ok().build();
    }
}
