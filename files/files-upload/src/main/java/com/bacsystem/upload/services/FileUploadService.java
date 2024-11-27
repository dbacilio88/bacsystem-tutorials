package com.bacsystem.upload.services;


import com.bacsystem.microservices.dtos.response.ProcessResponse;
import com.bacsystem.upload.factory.IFileUploadFactory;
import com.bacsystem.upload.services.contracts.IDataFileService;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * <b>FileUploadService</b>
 * <p>
 * This class specifies the requirements for the {@link FileUploadService} component,
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
public class FileUploadService implements IFileUploadService {

    private final IFileUploadFactory fileFactory;
    private final IDataFileService fileDataService;

    public FileUploadService(final IFileUploadFactory fileFactory,
                             final IDataFileService fileDataService) {
        this.fileFactory = fileFactory;
        this.fileDataService = fileDataService;
    }

    @Override
    public Mono<ProcessResponse> createFile(String type, FilePart file) {
        return this.fileFactory.factory(type)
                .flatMap(factory-> factory.createFile(type,file));
    }

    @Override
    public Mono<ProcessResponse> getFile(String uuid) {
        return this.fileDataService.findByUuId(uuid)
                .flatMap(fileData-> this.fileFactory.factory(fileData.getType())
                .flatMap(factory-> factory.getUploadFile(fileData)));
    }

    @Override
    public Mono<ProcessResponse> getFiles(String type) {
        return null;
    }
}
