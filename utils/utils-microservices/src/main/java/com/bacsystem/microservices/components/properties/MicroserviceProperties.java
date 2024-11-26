package com.bacsystem.microservices.components.properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <b>MicroserviceProperties</b>
 * <p>
 * This class specifies the requirements for the {@link MicroserviceProperties} component,
 * developed in accordance with the development standards established by christian.
 * Collaboration is encouraged for the enhancement and expansion of the bacsystem-tutorials.
 * </p>
 * <p>
 * <b>Usage:</b>
 * description here!
 * </p>
 *
 * @author christian
 * @author dbacilio88@outllok.es
 * @since 24/11/2024
 */

@Data
@Component
@ConfigurationProperties(prefix = "microservices")
public class MicroserviceProperties {
    @Value("${spring.application.name:name not available}")
    private String name;
    @Value("${server.port:port not available}")
    private String port;
    @Value("${spring.webflux.base-path:path not available}")
    private String path;
    @Value("${version:version not available}")
    private String version;
}