package com.bacsystem.upload.services.contracts;


import com.bacsystem.microservices.dtos.response.ProcessResponse;
import com.bacsystem.upload.dtos.request.PageStatusRequest;
import com.bacsystem.upload.repositories.entities.DataStatusEntity;
import reactor.core.publisher.Mono;

/**
 * <b>IDataStatusService</b>
 * <p>
 * This class specifies the requirements for the {@link IDataStatusService} component,
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

public interface IDataStatusService {
    Mono<ProcessResponse> findByUuId(String uuId);
    Mono<ProcessResponse> findById(Long id);
    Mono<ProcessResponse> findByName(String name);
    Mono<ProcessResponse> findAll(final PageStatusRequest statusRequest);
    Mono<DataStatusEntity> save(DataStatusEntity entity);
    Mono<DataStatusEntity> update(DataStatusEntity entity);
    Mono<DataStatusEntity> delete(Long id);
}
