package com.bacsystem.upload.repositories;


import com.bacsystem.upload.projections.IFileTypeInformation;
import com.bacsystem.upload.repositories.entities.DataTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <b>IDataTypeRepository</b>
 * <p>
 * This class specifies the requirements for the {@link IDataTypeRepository} component,
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
public interface IDataTypeRepository extends CrudRepository<DataTypeEntity,Long> {

    @Transactional(readOnly=true)
    @Query(value = "SELECT ft.id as id, ft.uuId as uuId, ft.name as name," +
            "ft.description as description, ft.created as created FROM DataTypeEntity ft " +
            "WHERE ft.name =:name")
    Optional<IFileTypeInformation> findByName(@Param("name") String name);

    @Transactional(readOnly=true)
    @Query(value = "SELECT ft.id as id, ft.uuId as uuId, ft.name as name, " +
            "ft.description as description, ft.created as created FROM DataTypeEntity ft " +
            "WHERE ft.uuId =:uuId")
    Optional<IFileTypeInformation> findByUuid(@Param("uuId") String uuId);

}
