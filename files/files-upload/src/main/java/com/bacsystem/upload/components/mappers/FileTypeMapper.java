package com.bacsystem.upload.components.mappers;


import com.bacsystem.upload.dtos.response.FileTypeResponse;
import com.bacsystem.upload.projections.IFileTypeInformation;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static com.bacsystem.upload.components.helpers.FileHelper.doOnFormatDate;

/**
 * <b>FileTypeMapper</b>
 * <p>
 * This class specifies the requirements for the {@link FileTypeMapper} component,
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
public class FileTypeMapper {
    public static Mono<FileTypeResponse> map(IFileTypeInformation typeInformation){
        return Mono.fromCallable(() -> {
            log.trace("type information: {}", typeInformation);
            FileTypeResponse response = new FileTypeResponse();
            response.setUuId(typeInformation.getUuId());
            response.setName(typeInformation.getName());
            response.setDescription(typeInformation.getDescription());
            doOnFormatDate(typeInformation.getCreated()).map(date-> {
                response.setCreated(date);
                return response;
            }).subscribe();
            log.trace("type information response: {}", response);
            return response;
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
