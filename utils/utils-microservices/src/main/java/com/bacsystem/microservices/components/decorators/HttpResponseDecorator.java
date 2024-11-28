package com.bacsystem.microservices.components.decorators;


import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * <b>HttpResponseDecorator</b>
 * <p>
 * This class specifies the requirements for the {@link HttpResponseDecorator} component,
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
public class HttpResponseDecorator extends ServerHttpResponseDecorator {

    private final DataBufferFactory factory;

    public HttpResponseDecorator(ServerHttpResponse delegate) {
        super(delegate);
        this.factory = super.bufferFactory();
        log.info("HttpResponseDecorator");
    }

    @NonNull
    @Override
    public Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
        log.info("writeWith from {} ", body);
        if (body instanceof Flux<?>) {
            return super.writeWith(Flux.from(body)
                    .map(data -> this.factory.wrap(contentRaw(data, body))));
        }
        if (body instanceof Mono) {
            return super.writeWith(Mono.from(body)
                    .map(data -> this.factory.wrap(contentRaw(data, body))));
        }
        return super.writeWith(body);
    }

    @NonNull
    @Override
    public Mono<Void> writeAndFlushWith(@NonNull Publisher<? extends Publisher<? extends DataBuffer>> body) {
        log.info("writeAndFlushWith from {} ", body);
        return super.writeAndFlushWith(body);
    }

    private byte[] contentRaw(DataBuffer buffer, Publisher<? extends DataBuffer> body) {
        final byte[] bytes = new byte[buffer.readableByteCount()];
        buffer.read(bytes);
        final String content = new String(bytes, StandardCharsets.UTF_8);
        return content.getBytes(StandardCharsets.UTF_8);
    }
}