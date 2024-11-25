package com.bacsystem.upload.services;


import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>IFileService</b>
 * <p>
 * This class specifies the requirements for the {@link IFileService} component,
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

public interface IFileService {

    Mono<String>uploadFile(final String type, final FilePart file);
    Mono<String>getFile(final String uuid);
    Mono<List<String>>getFiles(final String type);

}
