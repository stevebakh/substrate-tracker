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

@Path("/projects/{projectId}/issues")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class IssuesResource {
    @GET
    public Response getIssues(
            @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit,
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
