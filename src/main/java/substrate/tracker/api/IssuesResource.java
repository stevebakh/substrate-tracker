package substrate.tracker.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projects/{projectId}/issues")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class IssuesResource {
    @GET
    public Response getIssues(
            @QueryParam("offset") @DefaultValue("0") final Integer offset,
            @QueryParam("limit")  @DefaultValue("10") final Integer limit,
            @PathParam("projectId") final Long projectId
    ) {
        return Response.ok().build();
    }

    @POST
    public Response createIssue(
            @PathParam("projectId") final Long projectId
    ) {
        return Response.ok().build();
    }

    @GET
    @Path("/{issueId}")
    public Response getIssue(
            @PathParam("projectId") final Long projectId,
            @PathParam("issueId") final Long issueId
    ) {
        return Response.ok().build();
    }

    @DELETE
    @Path("/{issueId}")
    public Response deleteIssue(
            @PathParam("projectId") final Long projectId,
            @PathParam("issueId") final Long issueId
    ) {
        return Response.ok().build();
    }

    @PUT
    @Path("/{issueId}")
    public Response updateIssue(
            @PathParam("projectId") final Long projectId,
            @PathParam("issueId") final Long issueId
    ) {
        return Response.ok().build();
    }
}
