package com.bacsystem.upload.components.helpers;


import com.bacsystem.microservices.components.enums.ResponseCode;
import com.bacsystem.upload.components.exceptions.ApplicationException;
import com.bacsystem.upload.dtos.FileTypeConfiguration;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

import static io.micrometer.common.util.StringUtils.isBlank;

/**
 * <b>FileHelper</b>
 * <p>
 * This class specifies the requirements for the {@link FileHelper} component,
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
public class FileHelper {

    public static Mono<String> doOnFormatDate(Instant instant) {
        return Mono.justOrEmpty(instant)
                .flatMap(date->doOnInstantToIso(Date.from(date)))
                .switchIfEmpty(Mono.just(""));

    }
    public static Mono<String> doOnInstantToIso(Date date) {
        return Mono.justOrEmpty(date).map(d->{
                   SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                   format.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
                   return format.format(d);
                }).switchIfEmpty(Mono.just(""));
    }

    public static Mono<Boolean>doOnDateIsAfter(LocalDate start, LocalDate end) {
        return Mono.just(start)
                .filter(date->date.isAfter(end))
                .flatMap(date-> Mono.error(new ApplicationException("the date must be less than or equals to", ResponseCode.NOT_FOUND)))
                .switchIfEmpty(Mono.just(false))
                .hasElement();
    }

    public static Mono<Boolean>doOnDateIsNotNull(LocalDate start, LocalDate end) {
        return Mono.just(true)
                .filter(date->(start==null&& end!=null)||(start!=null&& end==null))
                .flatMap(date-> Mono.error(new ApplicationException("the date from and date to are required", ResponseCode.NOT_FOUND)))
                .switchIfEmpty(Mono.just(false))
                .hasElement();
    }
    public static Mono<LocalDate>doOnDate(LocalDate date, LocalDate defaultDate) {
        return Mono.justOrEmpty(date).switchIfEmpty(Mono.just(defaultDate));
    }

    public static Mono<Boolean>doOnValidateFileName(String fileName, FileTypeConfiguration fileTypeConfiguration) {
        return Mono.just(isBlank(fileName))
                .filter(file-> !file)
                .flatMap(file-> Mono.just(Objects.isNull(fileTypeConfiguration)||isBlank(fileTypeConfiguration.getFileNamePattern())|| fileName.matches(fileTypeConfiguration.getFileNamePattern())))
                .flatMap(Mono::just)
                .switchIfEmpty(Mono.just(false));
    }
}
