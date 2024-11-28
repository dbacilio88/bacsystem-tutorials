package com.bacsystem.upload.services.contracts;


import com.bacsystem.microservices.dtos.response.ProcessResponse;
import com.bacsystem.upload.repositories.entities.DataTypeEntity;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>IDataTypeService</b>
 * <p>
 * This class specifies the requirements for the {@link IDataTypeService} component,
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

public interface IDataTypeService {
    Mono<List<DataTypeEntity>> findAll();
    Mono<DataTypeEntity> findById(Long id);
    Mono<DataTypeEntity> findByName(String name);
    Mono<ProcessResponse> findByUuId(String uuId);
    Mono<DataTypeEntity> save(DataTypeEntity entity);
    Mono<DataTypeEntity> update(DataTypeEntity entity);
    Mono<DataTypeEntity> delete(Long id);
}
