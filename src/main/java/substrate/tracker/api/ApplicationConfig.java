package substrate.tracker.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class ApplicationConfig extends Application {
    // This class removes the need for web.xml servlet mapping configuration
}
