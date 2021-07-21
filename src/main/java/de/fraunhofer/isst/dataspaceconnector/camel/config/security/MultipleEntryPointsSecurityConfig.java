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
package de.fraunhofer.isst.dataspaceconnector.camel.config.security;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Creates a user with the "ADMIN" role for Spring Security.
 */
@Configuration
@EnableWebSecurity
@NoArgsConstructor
public class MultipleEntryPointsSecurityConfig {

    /**
     * The username.
     */
    @Value("${spring.security.user.name}")
    private String username;

    /**
     * The password.
     */
    @Value("${spring.security.user.password}")
    private String password;

    /**
     * Sets up a default admin user.
     *
     * @return the user details manager the admin user has been added to.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        final var manager = new InMemoryUserDetailsManager();
        manager.createUser(User
                .withUsername(username)
                .password(encoder().encode(password))
                .roles("ADMIN").build());
        return manager;
    }

    /**
     * Bean providing a password encoder.
     *
     * @return the password encoder.
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
