package com.bacsystem.upload.repositories;


import com.bacsystem.upload.repositories.entities.FileStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>IFileStatus</b>
 * <p>
 * This class specifies the requirements for the {@link IFileStatus} component,
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
public interface IFileStatus extends CrudRepository<FileStatus,Long> {




}
