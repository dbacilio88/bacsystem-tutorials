package com.bacsystem.upload.services.implementations;


import com.bacsystem.microservices.components.utils.Utility;
import com.bacsystem.upload.projections.IFileDataInformation;
import com.bacsystem.upload.repositories.IDataFileRepository;
import com.bacsystem.upload.repositories.entities.DataFileEntity;
import com.bacsystem.upload.services.contracts.IDataFileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>DataFileService</b>
 * <p>
 * This class specifies the requirements for the {@link DataFileService} component,
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
public class DataFileService implements IDataFileService {

    private final IDataFileRepository fileDataRepository;

    public DataFileService(final IDataFileRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }

    @Override
    public Mono<List<DataFileEntity>> findAll() {
        return null;
    }

    @Override
    public Mono<DataFileEntity> findById(Long id) {
        return null;
    }

    @Override
    public Mono<DataFileEntity> findByName(String name) {
        return null;
    }

    @Override
    public Mono<IFileDataInformation> findByUuId(String uuId) {
        return Utility.findEntity(() -> this.fileDataRepository.findByUuId(uuId),
                String.format("File Data uuid [%s]",uuId)
        );
    }

    @Override
    public Mono<DataFileEntity> save(DataFileEntity entity) {
        return null;
    }

    @Override
    public Mono<DataFileEntity> update(DataFileEntity entity) {
        return null;
    }

    @Override
    public Mono<DataFileEntity> delete(Long id) {
        return null;
    }
}
