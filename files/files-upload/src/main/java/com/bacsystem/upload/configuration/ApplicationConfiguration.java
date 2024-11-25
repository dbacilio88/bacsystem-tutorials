package com.bacsystem.upload.configuration;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * <b>ApplicationConfiguration</b>
 * <p>
 * This class specifies the requirements for the {@link ApplicationConfiguration} component,
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

@Configuration
@EnableWebFlux
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class})
public class ApplicationConfiguration implements WebFluxConfigurer {
}
