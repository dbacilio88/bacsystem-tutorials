package com.bacsystem.microservices.components.decorators;


import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;

/**
 * <b>HttpRequestDecorator</b>
 * <p>
 * This class specifies the requirements for the {@link HttpRequestDecorator} component,
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
public class HttpRequestDecorator extends ServerHttpRequestDecorator {
    public HttpRequestDecorator(ServerHttpRequest delegate) {
        super(delegate);
        log.info("HttpRequestDecorator");
    }

    @NonNull
    @Override
    public Flux<DataBuffer> getBody() {
        log.info("Request from {} ", super.getPath());
        return super.getBody();
    }
}
