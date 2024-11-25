package com.bacsystem.upload.factory;


import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * <b>FileUploadService</b>
 * <p>
 * This class specifies the requirements for the {@link FileUploadAbstract} component,
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
 * @since 23/11/2024
 */
@Log4j2
@Component
public abstract class FileUploadAbstract {


    public Mono<String> uploadFile(final String type, final FilePart filePart){
        log.info("upload file type [{}]", type);
        return Mono.just(filePart)
                .flatMap(file-> DataBufferUtils.join(filePart.content())
                        .flatMap(dataBuffer -> this.upload(dataBuffer))
                        .flatMap(data->{
                            return Mono.just(data);
                        }))
                .doOnSuccess(success-> log.info("successfully upload type {} file {} ",type,filePart.filename()))
                .doOnError(error->{
                    if (log.isDebugEnabled()) {
                        log.error(error);
                    }else{
                        log.error(error.getMessage());
                    }
                });
    }

    public abstract Mono<String> upload(DataBuffer dataBuffer);

}
