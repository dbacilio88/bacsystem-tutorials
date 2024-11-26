package com.bacsystem.microservices.dtos.response;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * <b>ApplicationResponse</b>
 * <p>
 * This class specifies the requirements for the {@link ApplicationResponse} component,
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
@Validated
@Component
public class ApplicationResponse {

    public Mono<ServerResponse> getResponse(final BaseResponse response, final int code) {
        log.info("res {}", response);
        return build(response, code);
    }

    public Mono<ServerResponse> getResponse(final int code) {
        return build(new BaseResponse(), code);
    }

    private Mono<ServerResponse> build(final BaseResponse response, final int code) {
        response.setCode(code);
        response.setDateTime(new Date());
        response.setMessage(response.getMessage());
        return ServerResponse.status(code)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(response));
    }

    public Mono<ResponseEntity<Object>> getResponseError(final BaseResponse response, final int code) {
        log.info("res {}", response);
        return buildError(response, code);
    }

    private Mono<ResponseEntity<Object>> buildError(final BaseResponse response, final int code) {
        response.setCode(code);
        response.setDateTime(new Date());
        response.setMessage(response.getMessage());
        response.setErrors(response.getErrors());

        return Mono.just(ResponseEntity.status(code)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response)
        );
    }
}
