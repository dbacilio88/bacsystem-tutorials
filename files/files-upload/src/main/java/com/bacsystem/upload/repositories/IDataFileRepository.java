package com.bacsystem.upload.repositories;


import com.bacsystem.upload.projections.IFileDataInformation;
import com.bacsystem.upload.repositories.entities.DataFileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <b>IDataFileRepository</b>
 * <p>
 * This class specifies the requirements for the {@link IDataFileRepository} component,
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
@Repository
public interface IDataFileRepository extends CrudRepository<DataFileEntity,Long> {

    @Transactional(readOnly=true)
    @Query(value = "SELECT fd.id as id, fd.uuId as uuId, fd.name as name, " +
            "fd.type.name as type, fd.status.name as status, fd.created as created " +
            "FROM DataFileEntity fd WHERE fd.uuId =:uuId")
    Optional<IFileDataInformation> findByUuId(@Param(value = "uuId") String uuId);
}
