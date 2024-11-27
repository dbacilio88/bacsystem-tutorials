package com.bacsystem.upload.handler;


import com.bacsystem.microservices.dtos.response.ApplicationResponse;
import com.bacsystem.microservices.dtos.response.ServerBaseResponse;
import com.bacsystem.upload.dtos.request.PageStatusRequest;
import com.bacsystem.upload.services.contracts.IDataStatusService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * <b>FileStatusHandler</b>
 * <p>
 * This class specifies the requirements for the {@link FileStatusHandler} component,
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
 * @since 23/11/2024
 */

@Log4j2
@Component
public class FileStatusHandler extends ServerBaseResponse {

    private final IDataStatusService fileStatusService;

    public FileStatusHandler(final ApplicationResponse applicationResponse,
                             final IDataStatusService fileStatusService) {
        super(applicationResponse);
        this.fileStatusService = fileStatusService;
    }
    public Mono<ServerResponse> doOnGetStatus(ServerRequest request) {
        final String uuId = request.pathVariable("uuId");
        log.info("init process get file status [{}]", uuId);
        return this.fileStatusService.findByUuId(uuId)
                .flatMap(this::response);
    }
    public Mono<ServerResponse> doOnGetAllStatus(ServerRequest request) {

        log.info("init process get all file status [{}]", request);
        return this.fileStatusService.findAll(PageStatusRequest.builder()
                        .page(1)
                        .build())
                .flatMap(this::response);
    }
    /*
    private final IFileUploadService fileUploadService;

    public FileStatusHandler(ApplicationResponse applicationResponse, IFileUploadService fileUploadService) {
        super(applicationResponse);
        this.fileUploadService = fileUploadService;
    }

    public Mono<ServerResponse>doOnGetStatus(ServerRequest request) {
        final String uuId = request.pathVariable("uuId");
        log.info("init process get file [{}]", uuId);
      return this.fileUploadService.getFile(uuId)
              .flatMap(this::response);
    }

    public Mono<ServerResponse>doOnSaveStatus(ServerRequest request) {
        final Mono<FilePart> filePartMono = request.bodyToMono(FilePart.class);
        log.info("init process create file [{}]", request);
        return filePartMono
                .flatMap(rq-> this.fileUploadService.createFile("TYPE",rq))
                .flatMap(this::response);

    }

     */
}
