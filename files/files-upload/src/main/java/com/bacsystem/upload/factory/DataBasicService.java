package com.bacsystem.upload.factory;


import com.bacsystem.upload.repositories.entities.DataFileEntity;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * <b>DataBasicService</b>
 * <p>
 * This class specifies the requirements for the {@link DataBasicService} component,
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
@Component("DATA_BASIC")
public class DataBasicService extends FileUploadAbstract {

    @Override
    public Mono<DataFileEntity> create(DataBuffer dataBuffer) {
        return null;
    }
}
