package com.bacsystem.upload.services.contracts;


import com.bacsystem.upload.projections.IFileDataInformation;
import com.bacsystem.upload.repositories.entities.FileDataEntity;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>IFileDataService</b>
 * <p>
 * This class specifies the requirements for the {@link IFileDataService} component,
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

public interface IFileDataService {
    //Mono<FileDataEntity> createFileDataInformation(final String fileName,final String type);
    //Mono<IFileDataInformation>getFileDataInformation(String uuId);

    Mono<List<FileDataEntity>> findAll();
    Mono<FileDataEntity> findById(Long id);
    Mono<FileDataEntity> findByName(String name);
    Mono<IFileDataInformation> findByUuId(String uuId);
    Mono<FileDataEntity> save(FileDataEntity entity);
    Mono<FileDataEntity> update(FileDataEntity entity);
    Mono<FileDataEntity> delete(Long id);
}
