package com.bacsystem.upload.services.implementations;


import com.bacsystem.microservices.components.utils.Utility;
import com.bacsystem.upload.projections.IFileDataInformation;
import com.bacsystem.upload.repositories.IFileDataRepository;
import com.bacsystem.upload.repositories.entities.FileDataEntity;
import com.bacsystem.upload.services.contracts.IFileDataService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>FileDataService</b>
 * <p>
 * This class specifies the requirements for the {@link FileDataService} component,
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
@Service
public class FileDataService implements IFileDataService {

    private final IFileDataRepository fileDataRepository;

    public FileDataService(final IFileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }

    @Override
    public Mono<List<FileDataEntity>> findAll() {
        return null;
    }

    @Override
    public Mono<FileDataEntity> findById(Long id) {
        return null;
    }

    @Override
    public Mono<FileDataEntity> findByName(String name) {
        return null;
    }

    @Override
    public Mono<IFileDataInformation> findByUuId(String uuId) {
        return Utility.findEntity(() -> this.fileDataRepository.findByUuId(uuId),
                String.format("File Data uuid [%s]",uuId)
        );
    }

    @Override
    public Mono<FileDataEntity> save(FileDataEntity entity) {
        return null;
    }

    @Override
    public Mono<FileDataEntity> update(FileDataEntity entity) {
        return null;
    }

    @Override
    public Mono<FileDataEntity> delete(Long id) {
        return null;
    }
}
