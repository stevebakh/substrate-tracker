package substrate.tracker.api;

import org.slf4j.Logger;
import substrate.tracker.dto.CollectionDTO;
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
    /**
     * Service object that interfaces with persistence layer.
     */
    @EJB
    private ProjectService projectService;

    /**
     * Mapper between Project DTOs and Project JPA entities.
     */
    @Inject
    private ProjectAssembler projectAssembler;

    /**
     * SLF4J Logger interface implementation.
     */
    @Inject
    private Logger logger;

    /**
     * Fetch a paginated collection of project records.
     *
     * @param limit The maximum number of projects to return
     * @param offset The start position in the list of projects
     * @return Response containing a collection of projects
     */
    @GET
    public Response getProjects(
            @QueryParam("limit") @DefaultValue("10") final int limit,
            @QueryParam("offset") @DefaultValue("0") final int offset
    ) {
        logger.info("Requesting projects with limit {} and offset {}", limit, offset);

        if (limit < 0 || offset < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        final CollectionDTO<ProjectDTO> collectionDTO = projectAssembler
                .assembleDTOs(projectService.getProjects(limit, offset));

        return Response.ok(collectionDTO).build();
    }

    /**
     * Submit a project record to be persisted to storage.
     *
     * @param projectDTO A project to persist in the database
     * @return Response with link to newly created project
     */
    @POST
    public Response createProject(final ProjectDTO projectDTO) {
        final Long projectId = projectService.createProject(
                projectAssembler.assembleEntity(projectDTO));

        return Response.created(URI.create(projectId.toString())).build();
    }

    /**
     * Fetch a specific project record by ID.
     *
     * @param projectId The ID of the project to retrieve
     * @return The project associated with the given ID
     */
    @GET
    @Path("/{projectId}")
    public Response getProject(@PathParam("projectId") final Long projectId) {
        final ProjectDTO projectDTO = projectAssembler.assembleDTO(
                projectService.findProjectById(projectId));

        return Response.ok(projectDTO).build();
    }

    /**
     * Delete a specific project by ID.
     *
     * @param projectId The ID of the project to delete
     * @return
     */
    @DELETE
    @Path("/{projectId}")
    public Response deleteProject(@PathParam("projectId") final Long projectId) {
        return null;
        //return Response.ok().build();
    }

    /**
     * Update a specific project by ID.
     *
     * @param projectId The ID of the project to update
     * @param projectDTO The updated project to persist in the database
     * @return ?
     */
    @PUT
    @Path("/{projectId}")
    public Response updateProject(
            @PathParam("projectId") final Long projectId,
            final ProjectDTO projectDTO
    ) {
        return null;
        //return Response.ok().build();
    }
}
