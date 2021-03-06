/*
 * Copyright 2021 Fraunhofer Institute for Software and Systems Engineering
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
public class RouteError {

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

    /**
     * Timestamp of when route error object was created.
     */
    private String timestamp;

}
