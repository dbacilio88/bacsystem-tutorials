package com.bacsystem.upload.services.implementations;


import com.bacsystem.microservices.components.utils.Utility;
import com.bacsystem.microservices.dtos.response.GenericResponse;
import com.bacsystem.microservices.dtos.response.ProcessResponse;
import com.bacsystem.upload.components.mappers.FileStatusMapper;
import com.bacsystem.upload.repositories.IFileStatusRepository;
import com.bacsystem.upload.repositories.entities.FileStatusEntity;
import com.bacsystem.upload.services.contracts.IFileStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>FileStatusService</b>
 * <p>
 * This class specifies the requirements for the {@link FileStatusService} component,
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
@RequiredArgsConstructor
public class FileStatusService implements IFileStatusService {

    private final IFileStatusRepository fileStatusRepository;

    @Override
    public Mono<List<FileStatusEntity>> findAll() {
        return null;
    }

    @Override
    public Mono<FileStatusEntity> findById(Long id) {
        return null;
    }

    @Override
    public Mono<FileStatusEntity> findByName(String name) {
        return null;
    }

    @Override
    public Mono<ProcessResponse> findByUuId(String uuId) {
        return Utility.findEntity(() -> this.fileStatusRepository.findByUuid(uuId),
                String.format("File Status uuid [%s]",uuId)
        )
                .flatMap(FileStatusMapper::map)
                .flatMap(response -> Mono.just(ProcessResponse.success(new GenericResponse<>(response))) );
    }

    @Override
    public Mono<FileStatusEntity> save(FileStatusEntity entity) {
        return null;
    }

    @Override
    public Mono<FileStatusEntity> update(FileStatusEntity entity) {
        return null;
    }

    @Override
    public Mono<FileStatusEntity> delete(Long id) {
        return null;
    }
}
