package com.bacsystem.upload.services.implementations;


import com.bacsystem.microservices.components.utils.Utility;
import com.bacsystem.microservices.dtos.response.DataPageableResponse;
import com.bacsystem.microservices.dtos.response.GenericResponse;
import com.bacsystem.microservices.dtos.response.PageableGenericResponse;
import com.bacsystem.microservices.dtos.response.ProcessResponse;
import com.bacsystem.upload.components.mappers.FileStatusMapper;
import com.bacsystem.upload.components.validators.IValidatorRequest;
import com.bacsystem.upload.dtos.request.PageStatusRequest;
import com.bacsystem.upload.repositories.IDataStatusRepository;
import com.bacsystem.upload.repositories.entities.DataStatusEntity;
import com.bacsystem.upload.services.contracts.IDataStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * <b>DataStatusService</b>
 * <p>
 * This class specifies the requirements for the {@link DataStatusService} component,
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
public class DataStatusService implements IDataStatusService {

    private final IDataStatusRepository fileStatusRepository;
    private final IValidatorRequest validatorRequest;

    @Override
    public Mono<ProcessResponse> findAll(final PageStatusRequest statusRequest) {
        return Mono.just(statusRequest)
                .flatMap(this.validatorRequest::validateDataStatus)
                .map(dataRequest -> PageRequest.of(dataRequest.getPage(),dataRequest.getLimit()))
                .flatMap(pageRequest -> Mono.fromCallable(()->this.fileStatusRepository.findAll(pageRequest))
                        .flatMap(FileStatusMapper::map)
                        .filter(pageResult-> pageResult.getTotalElements()!=0)
                        .flatMap(pageResult-> Mono.just(ProcessResponse.success(
                                new PageableGenericResponse<>(pageResult.getContent(), DataPageableResponse.build(pageResult,statusRequest)))))
                );
    }

    @Override
    public Mono<ProcessResponse> findById(Long id) {
        return Utility.findEntity(() -> this.fileStatusRepository.findById(id),
                String.format("file status id [%d]",id))
                .flatMap(FileStatusMapper::map)
                .flatMap(response-> Mono.just(ProcessResponse.success(new GenericResponse<>(response))));
    }

    @Override
    public Mono<ProcessResponse> findByName(String name) {
        return Utility.findEntity(() -> this.fileStatusRepository.findByName(name),
                String.format("file status name [%s]",name))
                .flatMap(FileStatusMapper::map)
                .flatMap(response->Mono.just(ProcessResponse.success(new GenericResponse<>(response))));
    }

    @Override
    public Mono<ProcessResponse> findByUuId(String uuId) {
        return Utility.findEntity(() -> this.fileStatusRepository.findByUuid(uuId),
                String.format("file status uuid [%s]",uuId)
        )
                .flatMap(FileStatusMapper::map)
                .flatMap(response -> Mono.just(ProcessResponse.success(new GenericResponse<>(response))) );
    }

    @Override
    public Mono<DataStatusEntity> save(DataStatusEntity entity) {
        return null;
    }

    @Override
    public Mono<DataStatusEntity> update(DataStatusEntity entity) {
        return null;
    }

    @Override
    public Mono<DataStatusEntity> delete(Long id) {
        return null;
    }
}
