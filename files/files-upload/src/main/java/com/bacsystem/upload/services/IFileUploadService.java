package com.bacsystem.upload.services;


import com.bacsystem.microservices.dtos.response.ProcessResponse;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

/**
 * <b>IFileUploadService</b>
 * <p>
 * This class specifies the requirements for the {@link IFileUploadService} component,
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

public interface IFileUploadService {

    Mono<ProcessResponse>createFile(final String type, final FilePart file);
    Mono<ProcessResponse>getFile(final String uuid);
    Mono<ProcessResponse>getFiles(final String type);

}
