package com.bacsystem.microservices.dtos.response;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * <b>ServerBaseResponse</b>
 * <p>
 * This class specifies the requirements for the {@link ServerBaseResponse} component,
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
@AllArgsConstructor
public class ServerBaseResponse {

    private final ApplicationResponse applicationResponse;

    public Mono<ServerResponse> response(final ProcessResponse processResponse) {
        return Mono.just(processResponse)
                .flatMap(process -> {
                    if (process.isError() && Objects.nonNull(process.getResponse())) {
                        log.info("response error {}", process.getResponse());
                        return this.applicationResponse.getResponse(process.getResponse().getCode());
                    }
                    if (process.isEmpty()) {
                        log.info("response empty {}", process.getResponse());
                        return this.applicationResponse.getResponse(process.getResponse().getCode());
                    }
                    log.info("response ok {}", process.getResponse());
                    return this.applicationResponse.getResponse(process.getResponse(), process.getResponseCode().getCode());
                })
                .onErrorResume(throwable -> this.applicationResponse.getResponse(500));
    }
}
