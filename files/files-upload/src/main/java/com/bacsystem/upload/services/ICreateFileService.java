package com.bacsystem.upload.services;


import com.bacsystem.upload.repositories.entities.FileDataEntity;
import org.springframework.core.io.buffer.DataBuffer;
import reactor.core.publisher.Mono;

/**
 * <b>ICreateFileService</b>
 * <p>
 * This class specifies the requirements for the {@link ICreateFileService} component,
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

public interface ICreateFileService {

    Mono<FileDataEntity>createFile(DataBuffer dataBuffer);
}
