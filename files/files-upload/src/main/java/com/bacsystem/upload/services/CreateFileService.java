package com.bacsystem.upload.services;


import com.bacsystem.upload.components.helpers.FileZipHelper;
import com.bacsystem.upload.repositories.entities.FileDataEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * <b>CreateFileService</b>
 * <p>
 * This class specifies the requirements for the {@link CreateFileService} component,
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
public class CreateFileService implements ICreateFileService {


    private final FileZipHelper fileZipHelper;
    @Override
    public Mono<FileDataEntity> createFile(DataBuffer dataBuffer) {
        return null;
    }
}
