package com.bacsystem.upload.components.mappers;


import com.bacsystem.upload.dtos.response.FileStatusResponse;
import com.bacsystem.upload.projections.IFileStatusInformation;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static com.bacsystem.upload.components.helpers.FileHelper.doOnFormatDate;

/**
 * <b>FileStatusMapper</b>
 * <p>
 * This class specifies the requirements for the {@link FileStatusMapper} component,
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
@UtilityClass
public class FileStatusMapper {

    public static Mono<FileStatusResponse> map(IFileStatusInformation statusInformation){
        return Mono.fromCallable(() -> {
            FileStatusResponse response = new FileStatusResponse();
            response.setUuId(statusInformation.getUuId());
            response.setName(statusInformation.getName());
            response.setDescription(statusInformation.getDescription());

            doOnFormatDate(statusInformation.getCreated()).map(date-> {
                response.setCreated(date);
                return response;
            }).subscribe();
            return response;
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
