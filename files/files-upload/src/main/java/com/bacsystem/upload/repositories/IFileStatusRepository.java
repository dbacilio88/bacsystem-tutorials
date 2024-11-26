package com.bacsystem.upload.repositories;


import com.bacsystem.upload.projections.IFileStatusInformation;
import com.bacsystem.upload.repositories.entities.FileStatusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <b>IFileStatusRepository</b>
 * <p>
 * This class specifies the requirements for the {@link IFileStatusRepository} component,
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
 * @since 24/11/2024
 */

@Repository
public interface IFileStatusRepository extends CrudRepository<FileStatusEntity,Long> {


    @Transactional(readOnly=true)
    @Query(value = "SELECT fs.id as id, fs.uuId as uuId, fs.name as name," +
            "fs.description as description, fs.created as created FROM FileStatusEntity fs " +
            "WHERE fs.name =:name")
    Optional<IFileStatusInformation> findByName(@Param("name") String name);

    @Transactional(readOnly=true)
    @Query(value = "SELECT fs.id as id, fs.uuId as uuId, fs.name as name," +
            "fs.description as description, fs.created as created FROM FileStatusEntity fs " +
            "WHERE fs.uuId =:uuId")
    Optional<IFileStatusInformation> findByUuid(@Param("uuId") String uuId);

}
