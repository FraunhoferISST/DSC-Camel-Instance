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
package de.fraunhofer.isst.dataspaceconnector.camel.controller;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that returns a response for the application's root path.
 */
@RestController
@RequestMapping
@NoArgsConstructor
public class MainController {

    /**
     * Can be called to check if the application is running.
     *
     * @return a response entity with code 200
     */
    @GetMapping
    public ResponseEntity<String> rootPath() {
        return new ResponseEntity<>("Camel instance is running!", HttpStatus.OK);
    }

}
