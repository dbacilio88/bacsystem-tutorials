package com.bacsystem.upload.handler.routes;


import com.bacsystem.upload.handler.FileStatusHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * <b>FileStatusRouterFunction</b>
 * <p>
 * This class specifies the requirements for the {@link FileStatusRouterFunction} component,
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
public class FileStatusRouterFunction {

    private final FileStatusHandler fileStatusHandler;

    public FileStatusRouterFunction(FileStatusHandler fileStatusHandler) {
        this.fileStatusHandler = fileStatusHandler;
    }

    @Bean
    public WebProperties.Resources webProperties() {
        return new WebProperties.Resources();
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/status/id/{uuId}"), fileStatusHandler::doOnGetStatus);
                //.andRoute(POST("/status"),this.fileStatusHandler::doOnSaveStatus);

    }
}
