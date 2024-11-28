package com.bacsystem.upload.handler.routes;


import com.bacsystem.upload.handler.FileStatusHandler;
import com.bacsystem.upload.handler.FileTypeHandler;
import com.bacsystem.upload.handler.FileUploadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * <b>FileRouterFunction</b>
 * <p>
 * This class specifies the requirements for the {@link FileRouterFunction} component,
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
 * @since 25/11/2024
 */
@Log4j2
@Configuration
@RequiredArgsConstructor
public class FileRouterFunction {

    private final FileTypeHandler fileTypeHandler;
    private final FileStatusHandler fileStatusHandler;
    private final FileUploadHandler fileUploadHandler;

    @Bean
    public WebProperties.Resources webProperties() {
        return new WebProperties.Resources();
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/status/id/{uuId}"), fileStatusHandler::doOnGetStatus)
                .andRoute(GET("/status"), fileStatusHandler::doOnGetAllStatus)
                .andRoute(GET("/type/id/{uuId}"),fileTypeHandler::doOnGetType)
                .andRoute(POST("/upload").and(RequestPredicates.accept(MediaType.MULTIPART_FORM_DATA)),fileUploadHandler::doOnFileUpload);

    }

}
