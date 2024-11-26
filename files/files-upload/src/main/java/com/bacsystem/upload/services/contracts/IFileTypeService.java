package com.bacsystem.upload.services.contracts;


import com.bacsystem.microservices.dtos.response.ProcessResponse;
import com.bacsystem.upload.repositories.entities.FileTypeEntity;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>IFileTypeService</b>
 * <p>
 * This class specifies the requirements for the {@link IFileTypeService} component,
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

public interface IFileTypeService {
    Mono<List<FileTypeEntity>> findAll();
    Mono<FileTypeEntity> findById(Long id);
    Mono<FileTypeEntity> findByName(String name);
    Mono<ProcessResponse> findByUuId(String name);
    Mono<FileTypeEntity> save(FileTypeEntity entity);
    Mono<FileTypeEntity> update(FileTypeEntity entity);
    Mono<FileTypeEntity> delete(Long id);
}
