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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * This class takes care of the security configuration so that only users with the "ADMIN" role can
 * access endpoints behinde "/api".
 */
@Configuration
@NoArgsConstructor
public class SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Override
    public final void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .formLogin().disable()
                .antMatcher("/api/**")
                .authorizeRequests().anyRequest().hasRole("ADMIN")
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint());
        httpSecurity.headers().frameOptions().disable();
    }

    /**
     * Bean providing an entry point to the admin realm.
     *
     * @return the entry point.
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        final var entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("admin realm");
        return entryPoint;
    }

}
