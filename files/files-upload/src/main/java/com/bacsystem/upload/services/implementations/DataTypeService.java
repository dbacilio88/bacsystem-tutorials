package com.bacsystem.upload.services.implementations;


import com.bacsystem.microservices.components.utils.Utility;
import com.bacsystem.microservices.dtos.response.GenericResponse;
import com.bacsystem.microservices.dtos.response.ProcessResponse;
import com.bacsystem.upload.components.mappers.FileTypeMapper;
import com.bacsystem.upload.repositories.IDataTypeRepository;
import com.bacsystem.upload.repositories.entities.DataTypeEntity;
import com.bacsystem.upload.services.contracts.IDataTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>DataTypeService</b>
 * <p>
 * This class specifies the requirements for the {@link DataTypeService} component,
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
public class DataTypeService implements IDataTypeService {

    private final IDataTypeRepository fileTypeRepository;

    @Override
    public Mono<List<DataTypeEntity>> findAll() {
        return null;
    }

    @Override
    public Mono<DataTypeEntity> findById(Long id) {
        return null;
    }

    @Override
    public Mono<DataTypeEntity> findByName(String name) {
        return null;
    }

    @Override
    public Mono<ProcessResponse> findByUuId(String uuId) {
        return Utility.findEntity(() -> this.fileTypeRepository.findByUuid(uuId),
                String.format("File Type uuid [%s]",uuId))
                .flatMap(FileTypeMapper::map)
                .flatMap(response-> Mono.just(ProcessResponse.success(new GenericResponse<>(response))));
    }

    @Override
    public Mono<DataTypeEntity> save(DataTypeEntity entity) {
        return null;
    }

    @Override
    public Mono<DataTypeEntity> update(DataTypeEntity entity) {
        return null;
    }

    @Override
    public Mono<DataTypeEntity> delete(Long id) {
        return null;
    }
}
