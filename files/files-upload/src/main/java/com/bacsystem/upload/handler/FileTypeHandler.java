package com.bacsystem.upload.handler;


import com.bacsystem.microservices.dtos.response.ApplicationResponse;
import com.bacsystem.microservices.dtos.response.ServerBaseResponse;
import com.bacsystem.upload.services.contracts.IDataTypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * <b>FileTypeHandler</b>
 * <p>
 * This class specifies the requirements for the {@link FileTypeHandler} component,
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
@Component
public class FileTypeHandler extends ServerBaseResponse {

    private final IDataTypeService fileTypeService;
    public FileTypeHandler(ApplicationResponse applicationResponse, IDataTypeService fileTypeService) {
        super(applicationResponse);
        this.fileTypeService = fileTypeService;
    }

    public Mono<ServerResponse> doOnGetType(ServerRequest request) {
        final String uuId = request.pathVariable("uuId");
        log.info("init process get file type [{}]", uuId);
        return this.fileTypeService.findByUuId(uuId)
                .flatMap(this::response);
    }
}
