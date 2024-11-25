package com.bacsystem.upload.services;


import com.bacsystem.upload.factory.IFileFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>FileService</b>
 * <p>
 * This class specifies the requirements for the {@link FileService} component,
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
@Service
public class FileService implements IFileService {

    private final IFileFactory fileFactory;

    public FileService(IFileFactory fileFactory) {
        this.fileFactory = fileFactory;
    }

    @Override
    public Mono<String> uploadFile(String type, FilePart file) {
        return this.fileFactory.fileFactory(type)
                .flatMap(service-> service.uploadFile(type, file));
    }

    @Override
    public Mono<String> getFile(String uuid) {
        return null;
    }

    @Override
    public Mono<List<String>> getFiles(String type) {
        return null;
    }
}
