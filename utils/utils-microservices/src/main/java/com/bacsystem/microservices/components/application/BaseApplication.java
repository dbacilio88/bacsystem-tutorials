package com.bacsystem.microservices.components.application;


import com.bacsystem.microservices.components.properties.MicroserviceProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;

/**
 * <b>BaseApplication</b>
 * <p>
 * This class specifies the requirements for the {@link BaseApplication} component,
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
@Log4j2
@Configuration
public class BaseApplication implements ApplicationListener<ContextRefreshedEvent> {

    private final MicroserviceProperties microserviceProperties;

    public BaseApplication(MicroserviceProperties microserviceProperties) {
        this.microserviceProperties = microserviceProperties;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        log.info("************************ MICROSERVICE APP ADMIN CONSOLE ************************************");
        log.info("Application Name: {}", this.microserviceProperties.getName());
        log.info("Application Version: {}", this.microserviceProperties.getVersion());
        log.info("Application Port: {}", this.microserviceProperties.getPort());
        log.info("Application Path: {}", this.microserviceProperties.getPath());
        log.info("************************ MICROSERVICE APP ADMIN CONSOLE ************************************");
    }
}