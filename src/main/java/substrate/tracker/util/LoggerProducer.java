package substrate.tracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Produce an instance of an SLF4J API Logger implementation that
 * can be injected into beans using CDI.
 */
public class LoggerProducer {
    /**
     * Retrieve an instance of an SLF4J Logger implementation to be
     * injected into a bean with a field/method annotated with @Inject.
     *
     * @param injectionPoint The field/method in the target class annotated with @Inject
     * @return SLF4J logger to be injected
     */
    @Produces
    public Logger produceLogger(final InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(
                injectionPoint.getMember().getDeclaringClass().getName());
    }
}
