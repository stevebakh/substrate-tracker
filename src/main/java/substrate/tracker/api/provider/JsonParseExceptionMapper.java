package substrate.tracker.api.provider;

import org.codehaus.jackson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Map JSON parse exceptions thrown by Jackson to return a
 * 400 Bad Response, rather than a 500 Server Error response.
 * This will occur when malformed JSON is submitted to the
 * resource by a client.
 */
@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
    /**
     * SLF4J Logger interface implementation.
     */
    private static Logger logger = LoggerFactory
            .getLogger(ParameterExceptionMapper.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Response toResponse(final JsonParseException exception) {
        logger.warn("Failed to parse JSON", exception);
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
