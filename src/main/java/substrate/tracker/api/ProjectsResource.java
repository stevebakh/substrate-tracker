package substrate.tracker.api;

import substrate.tracker.dto.ProjectDTO;
import substrate.tracker.dto.assembler.ProjectAssembler;
import substrate.tracker.service.ProjectService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Stateless
@Path("/projects")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ProjectsResource {

    @EJB
    private ProjectService projectService;

    @Inject
    private ProjectAssembler projectAssembler;

    @GET
    public Response getProjects(
            @QueryParam("limit") @DefaultValue("10") final int limit,
            @QueryParam("offset") @DefaultValue("0") final int offset
    ) {
        if (limit < 0 || offset < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }

    @POST
    public Response createProject(final ProjectDTO projectDTO) {
        final Long projectId = projectService.createProject(
                projectAssembler.assembleEntity(projectDTO));

        return Response.created(URI.create(projectId.toString())).build();
    }

    @GET
    @Path("/{projectId}")
    public Response getProject(@PathParam("projectId") final Long projectId) {
        final ProjectDTO projectDTO = projectAssembler.assembleDTO(
                projectService.findProjectById(projectId));

        return Response.ok(projectDTO).build();
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
            final ProjectDTO projectDTO
    ) {
        return Response.ok().build();
    }
}
