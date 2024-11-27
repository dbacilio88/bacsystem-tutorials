package com.bacsystem.upload.repositories;


import com.bacsystem.upload.projections.IFileStatusInformation;
import com.bacsystem.upload.repositories.entities.DataStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <b>IDataStatusRepository</b>
 * <p>
 * This class specifies the requirements for the {@link IDataStatusRepository} component,
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
public interface IDataStatusRepository extends CrudRepository<DataStatusEntity,Long> {


    @Transactional(readOnly=true)
    @Query(value = "SELECT fs.id as id, fs.uuId as uuId, fs.name as name," +
            "fs.description as description, fs.created as created FROM DataStatusEntity fs " +
            "WHERE fs.name =:name")
    Optional<IFileStatusInformation> findByName(@Param("name") String name);

    @Transactional(readOnly=true)
    @Query(value = "SELECT fs.id as id, fs.uuId as uuId, fs.name as name," +
            "fs.description as description, fs.created as created FROM DataStatusEntity fs " +
            "WHERE fs.uuId =:uuId")
    Optional<IFileStatusInformation> findByUuid(@Param("uuId") String uuId);

    @Transactional(readOnly=true)
    @Query(value = "SELECT fs.id as id, fs.uuId as uuId, fs.name as name," +
            "fs.description as description, fs.created as created FROM DataStatusEntity fs",
            countQuery = "SELECT COUNT(fs.id) FROM DataStatusEntity fs"
    )
    Page<IFileStatusInformation> findAll(@Param("pageable") Pageable pageable);

}
