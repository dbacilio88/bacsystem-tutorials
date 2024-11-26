package com.bacsystem.upload.components.helpers;


import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

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
}
