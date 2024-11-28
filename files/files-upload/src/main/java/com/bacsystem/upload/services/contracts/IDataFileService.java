package com.bacsystem.upload.services.contracts;


import com.bacsystem.upload.projections.IFileDataInformation;
import com.bacsystem.upload.repositories.entities.DataFileEntity;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>IDataFileService</b>
 * <p>
 * This class specifies the requirements for the {@link IDataFileService} component,
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

public interface IDataFileService {
    //Mono<DataFileEntity> createFileDataInformation(final String fileName,final String type);
    //Mono<IFileDataInformation>getFileDataInformation(String uuId);

    Mono<List<DataFileEntity>> findAll();
    Mono<DataFileEntity> findById(Long id);
    Mono<DataFileEntity> findByName(String name);
    Mono<IFileDataInformation> findByUuId(String uuId);
    Mono<DataFileEntity> save(DataFileEntity entity);
    Mono<DataFileEntity> update(DataFileEntity entity);
    Mono<DataFileEntity> delete(Long id);
}
