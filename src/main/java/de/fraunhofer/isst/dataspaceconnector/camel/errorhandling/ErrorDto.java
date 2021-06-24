package de.fraunhofer.isst.dataspaceconnector.camel.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO for sending error information to the Configuration Manager.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ErrorDto {

    /**
     * ID of the route where an error occurred.
     */
    private String routeId;

    /**
     * The endpoint where the error occurred.
     */
    private String endpoint;

    /**
     * The exception message.
     */
    private String message;

}
