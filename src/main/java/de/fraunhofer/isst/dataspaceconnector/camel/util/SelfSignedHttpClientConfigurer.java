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
package de.fraunhofer.isst.dataspaceconnector.camel.util;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;

import lombok.NoArgsConstructor;
import org.apache.camel.component.http.HttpClientConfigurer;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An HttpClientConfigurer that can be used in routes when an HTTP endpoint that uses a self-signed
 * certificate is called. This should only be used in test environments as it disables the hostname
 * verification!
 */
@NoArgsConstructor
public class SelfSignedHttpClientConfigurer implements HttpClientConfigurer {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(SelfSignedHttpClientConfigurer.class);

    /**
     * Configures the HTTP client to be able to work with self-signed certificates by disabling
     * the hostname verification.
     *
     * @param httpClientBuilder the HttpClientBuilder.
     */
    @Override
    public void configureHttpClient(final HttpClientBuilder httpClientBuilder) {
        try {
            final SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, (x509CertChain, authType) -> true).build();
            httpClientBuilder.setSSLContext(sslContext)
                    .setConnectionManager(new PoolingHttpClientConnectionManager(RegistryBuilder
                            .<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.INSTANCE)
                            .register("https", new SSLConnectionSocketFactory(sslContext,
                                    NoopHostnameVerifier.INSTANCE))
                            .build()));
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Unable to create HttpClientConfigurer for self-signed certificates. "
                        + "[exception=({})]", e.getMessage(), e);
            }
        }
    }

}
