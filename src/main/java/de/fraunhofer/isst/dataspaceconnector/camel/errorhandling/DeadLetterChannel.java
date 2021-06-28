package de.fraunhofer.isst.dataspaceconnector.camel.errorhandling;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Defines the route used for handling errors, which will send an error description to the
 * Configuration Manager endpoint defined in application.properties.
 */
@Component
public class DeadLetterChannel extends RouteBuilder {

    /**
     * The Configuration Manager endpoint for logging errors.
     */
    @Value("${config-manager.error-api.url}")
    private String errorLogEndpoint;

    /**
     * Configures the error route. The error route uses a processor to create an {@link RouteError}
     * and then sends this to the Configuration Manager.
     *
     * @throws Exception if an error occurs writing the ErrorDto as JSON.
     */
    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .process(exchange -> {
                    final var cause = exchange
                            .getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    log.warn("Failed to send error logs to Configuration Manager: {}",
                            cause.getMessage());
                })
                .handled(true);

        from("direct:deadLetterChannel")
                .process("dlcProcessor")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .to(errorLogEndpoint);
    }

}
