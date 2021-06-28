package de.fraunhofer.isst.dataspaceconnector.camel.config.context;

import org.apache.camel.builder.DeadLetterChannelBuilder;
import org.apache.camel.processor.errorhandler.RedeliveryPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines beans required for error handling.
 */
@Configuration
public class ErrorHandlerConfig {

    /**
     * Returns a DeadLetterChannelBuilder instance that routes all failed exchanges to the
     * designated error handler route.
     *
     * @return the DeadLetterChannelBuilder
     */
    @Bean
    public DeadLetterChannelBuilder errorHandler() {
        final var dlcBuilder = new DeadLetterChannelBuilder();
        dlcBuilder.setDeadLetterUri("direct:deadLetterChannel");
        dlcBuilder.setRedeliveryPolicy(new RedeliveryPolicy().disableRedelivery());
        return dlcBuilder;
    }

}
