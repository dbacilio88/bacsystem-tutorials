package com.bacsystem.upload.handler;


import com.bacsystem.microservices.dtos.response.ApplicationResponse;
import com.bacsystem.microservices.dtos.response.ServerBaseResponse;
import com.bacsystem.upload.dtos.request.FileUploadRequest;
import com.bacsystem.upload.services.IFileUploadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * <b>FileUploadHandler</b>
 * <p>
 * This class specifies the requirements for the {@link FileUploadHandler} component,
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
 * @since 27/11/2024
 */
@Log4j2
@Component
public class FileUploadHandler extends ServerBaseResponse {

    private final IFileUploadService fileUploadService;

    public FileUploadHandler(final ApplicationResponse applicationResponse,
                             final IFileUploadService fileUploadService) {
        super(applicationResponse);
        this.fileUploadService = fileUploadService;
    }

    public Mono<ServerResponse> doOnFileUpload(ServerRequest request) {
        log.info("init process get file request [{}]", request);
        return request.multipartData()
                        .flatMap(formData-> Mono.just(FileUploadRequest.builder()
                                .file((FilePart) formData.getFirst("file"))
                                        .type("DATA_BASIC")
                                .build()))
                .flatMap(integer -> this.fileUploadService.createFile(integer.getType(),integer.getFile()))
                .flatMap(this::response);
    }
}
