package com.bacsystem.upload.services.implementations;


import com.bacsystem.microservices.dtos.response.ProcessResponse;
import com.bacsystem.upload.repositories.entities.FileTypeEntity;
import com.bacsystem.upload.services.contracts.IFileTypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>FileTypeService</b>
 * <p>
 * This class specifies the requirements for the {@link FileTypeService} component,
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
public class FileTypeService implements IFileTypeService {
    @Override
    public Mono<List<FileTypeEntity>> findAll() {
        return null;
    }

    @Override
    public Mono<FileTypeEntity> findById(Long id) {
        return null;
    }

    @Override
    public Mono<FileTypeEntity> findByName(String name) {
        return null;
    }

    @Override
    public Mono<ProcessResponse> findByUuId(String name) {
        return null;
    }

    @Override
    public Mono<FileTypeEntity> save(FileTypeEntity entity) {
        return null;
    }

    @Override
    public Mono<FileTypeEntity> update(FileTypeEntity entity) {
        return null;
    }

    @Override
    public Mono<FileTypeEntity> delete(Long id) {
        return null;
    }
}
