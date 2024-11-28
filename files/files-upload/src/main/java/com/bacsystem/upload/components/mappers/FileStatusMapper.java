package com.bacsystem.upload.components.mappers;


import com.bacsystem.upload.dtos.response.DataStatusResponse;
import com.bacsystem.upload.projections.IFileStatusInformation;
import com.bacsystem.upload.repositories.entities.DataStatusEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;

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

    public static Mono<Page<DataStatusResponse>> map(Page<IFileStatusInformation> statusInformation){
       return Mono.fromCallable(() -> statusInformation.map(information->{
           DataStatusResponse response = new DataStatusResponse();
           response.setUuId(information.getUuId());
           response.setName(information.getName());
           response.setDescription(information.getDescription());
           doOnFormatDate(information.getCreated()).map(date-> {
               response.setCreated(date);
               return response;
           }).subscribe();
           return response;
       }));
    }

    public static Mono<DataStatusResponse> map(IFileStatusInformation statusInformation){
        return getFileStatusResponseMono(statusInformation.getUuId(), statusInformation.getName(), statusInformation.getDescription(), statusInformation.getCreated());
    }

    public static Mono<DataStatusResponse> map(DataStatusEntity statusEntity){
        return getFileStatusResponseMono(statusEntity.getUuId(), statusEntity.getName(), statusEntity.getDescription(), statusEntity.getCreated());
    }

    private static Mono<DataStatusResponse> getFileStatusResponseMono(String uuId, String name, String description, Instant created) {
        return Mono.fromCallable(() -> {
            DataStatusResponse response = new DataStatusResponse();
            response.setUuId(uuId);
            response.setName(name);
            response.setDescription(description);
            doOnFormatDate(created).map(date-> {
                response.setCreated(date);
                return response;
            }).subscribe();
            return response;
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
