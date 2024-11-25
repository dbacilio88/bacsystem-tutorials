package com.bacsystem.upload.handler;


import com.bacsystem.upload.components.utils.response.ApplicationResponse;
import com.bacsystem.upload.components.utils.response.ServerResponseBase;
import com.bacsystem.upload.services.IFileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * <b>FilesHandler</b>
 * <p>
 * This class specifies the requirements for the {@link FilesHandler} component,
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
public class FilesHandler extends ServerResponseBase{

    private final IFileService fileService;

    public FilesHandler(ApplicationResponse applicationResponse, IFileService fileService) {
        super(applicationResponse);
        this.fileService = fileService;
    }

    public Mono<ServerResponse>doOnGetFiles(ServerRequest request) {
      return ServerResponse.ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(fileService.getFiles(""), FilePart.class);

    }

    public Mono<ServerResponse>doOnUploadFile(ServerRequest request) {
        final Mono<FilePart> filePartMono = request.bodyToMono(FilePart.class);
        return filePartMono
                .flatMap(rq-> this.fileService.uploadFile("TYPE",rq))
                .flatMap(response-> ServerResponse
                        .created(URI.create("/file"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(response), FilePart.class));

    }
}
