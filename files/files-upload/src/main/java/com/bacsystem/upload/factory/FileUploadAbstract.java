package com.bacsystem.upload.factory;


import com.bacsystem.microservices.dtos.response.GenericResponse;
import com.bacsystem.microservices.dtos.response.ProcessResponse;
import com.bacsystem.upload.dtos.response.FileDataResponse;
import com.bacsystem.upload.projections.IFileDataInformation;
import com.bacsystem.upload.repositories.entities.FileDataEntity;
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


    public Mono<ProcessResponse> createFile(final String type, final FilePart filePart){
        log.info("upload file type [{}]", type);

        return Mono.just(filePart)
                .flatMap(file-> DataBufferUtils.join(filePart.content())
                        .flatMap(dataBuffer -> this.create(dataBuffer))
                        .flatMap(entity->{
                            final FileDataResponse response = FileDataResponse
                                    .builder()
                                    .id(entity.getId())
                                    .uuId(entity.getUuId())
                                    .build();
                            return Mono.just(ProcessResponse.empty(new GenericResponse<>(response)));
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

    public abstract Mono<FileDataEntity> create(DataBuffer dataBuffer);

    public Mono<ProcessResponse>getUploadFile(final IFileDataInformation fileDataInformation){
        return this.setFileDataResponseMono(fileDataInformation)
                .flatMap(response-> Mono.just(ProcessResponse.empty(new GenericResponse<>(response))));
    }

    private Mono<FileDataResponse>setFileDataResponseMono(final IFileDataInformation fileDataInformation){
        return Mono.just(fileDataInformation)
                .map(fileData->{
                    return FileDataResponse.builder().build();
                }).flatMap(response->{
                    return Mono.just(response);
                }).flatMap(response->{
                    return Mono.just(response);
                });
    }

}
