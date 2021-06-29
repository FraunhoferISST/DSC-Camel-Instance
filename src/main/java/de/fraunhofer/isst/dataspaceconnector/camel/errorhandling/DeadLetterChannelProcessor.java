package de.fraunhofer.isst.dataspaceconnector.camel.errorhandling;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * Prepares an {@link RouteError} for a failed exchange.
 */
@Component("dlcProcessor")
@Log4j2
public class DeadLetterChannelProcessor implements Processor {

    /**
     * Writes the ErrorDto as JSON.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reads the route's ID, the endpoint where the exchange failed and the exception that caused
     * the failure from an Exchange object and creates an {@link RouteError}. The ErrorDto is set as
     * the body of the exchange's message.
     *
     * @param exchange the failed exchange.
     * @throws Exception if an error occurs writing the error DTO as JSON.
     */
    @Override
    public void process(final Exchange exchange) throws Exception {
        final var routeId = exchange.getProperty("CamelFailureRouteId", String.class);
        final var failureEndpoint = exchange.getProperty("CamelFailureEndpoint", String.class);
        final var cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

        final var routeError = new RouteError(routeId, failureEndpoint, cause.getMessage(),
                LocalDateTime.now().toString());

        log.warn("Caught an exception during route execution: {}", routeError);

        exchange.getIn().setBody(objectMapper.writeValueAsString(routeError));
    }

}
