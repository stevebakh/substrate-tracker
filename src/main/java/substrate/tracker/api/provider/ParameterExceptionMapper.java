package substrate.tracker.api.provider;

import com.sun.jersey.api.ParamException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Map parameter marshaling exceptions to return a HTTP
 * 400 Bad Response, rather than a 404 Not Found response.
 * This will occur when JAX-RS is unable to convert a
 * parameter value to the type specified in the controller.
 */
@Provider
public class ParameterExceptionMapper implements ExceptionMapper<ParamException> {
    @Override
    public Response toResponse(ParamException exception) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
