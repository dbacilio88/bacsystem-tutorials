package com.bacsystem.upload.services.contracts;


import com.bacsystem.microservices.dtos.response.ProcessResponse;
import com.bacsystem.upload.repositories.entities.FileStatusEntity;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>IFileStatusService</b>
 * <p>
 * This class specifies the requirements for the {@link IFileStatusService} component,
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

public interface IFileStatusService {
    Mono<List<FileStatusEntity>> findAll();
    Mono<FileStatusEntity> findById(Long id);
    Mono<FileStatusEntity> findByName(String name);
    Mono<ProcessResponse> findByUuId(String uuId);
    Mono<FileStatusEntity> save(FileStatusEntity entity);
    Mono<FileStatusEntity> update(FileStatusEntity entity);
    Mono<FileStatusEntity> delete(Long id);
}
