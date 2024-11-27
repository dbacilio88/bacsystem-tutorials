package com.bacsystem.upload.components.validators;


import com.bacsystem.microservices.components.enums.ResponseCode;
import com.bacsystem.microservices.dtos.request.PageRequest;
import com.bacsystem.upload.components.exceptions.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.bacsystem.upload.components.helpers.FileHelper.*;

/**
 * <b>ValidatorRequest</b>
 * <p>
 * This class specifies the requirements for the {@link ValidatorRequest} component,
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
 * @since 26/11/2024
 */
@Service
@RequiredArgsConstructor
public class ValidatorRequest implements IValidatorRequest {
    @Override
    public Mono<PageRequest> validateDataStatus(PageRequest pageRequest) {
        return validateDate(pageRequest)
                .flatMap(this::buildRequest);
    }

    private Mono<PageRequest> validateDate(PageRequest pageRequest) {
        return Mono.just(pageRequest)
                .filter(request-> request.getStartDate()!= null && request.getEndDate()!=null)
                .flatMap(request-> doOnDateIsAfter(request.getStartDate(), LocalDate.now())
                        .flatMap(isAfter-> Mono.just(pageRequest)))
                .flatMap(request -> doOnDateIsAfter(request.getStartDate(),request.getEndDate())
                        .flatMap(isAfter-> Mono.just(pageRequest))
                ).flatMap(this::validateLimitDay)
                .switchIfEmpty(Mono.just(pageRequest))
                .flatMap(request-> doOnDateIsNotNull(request.getStartDate(),request.getEndDate())
                        .flatMap(exist-> Mono.just(pageRequest)));
    }
    private Mono<PageRequest> validateLimitDay(PageRequest pageRequest){
        return Mono.just(pageRequest)
                .flatMap(request->
                    Mono.fromCallable(() ->ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate()))
                            .filter(differenceDays -> differenceDays >180)
                            .switchIfEmpty(Mono.error(new ApplicationException("Days exceeds limit of 180", ResponseCode.NOT_FOUND)))
                            .map(aLong -> request)
                );
    }
    private Mono<PageRequest> buildRequest(PageRequest pageRequest){
        return Mono.just(pageRequest)
                .flatMap(request->doOnDate(request.getStartDate(),LocalDate.now().minusDays(15))
                        .flatMap(startDate->{
                            pageRequest.setStartDate(startDate);
                            return Mono.just(pageRequest);
                        }))
                .flatMap(request-> doOnDate(request.getEndDate(),LocalDate.now())
                        .flatMap(endDate->{
                            pageRequest.setEndDate(endDate);
                            return Mono.just(pageRequest);
                        }))
                .then(Mono.just(10).flatMap(limit->{
                    pageRequest.setPage(pageRequest.getPage()-1);
                    pageRequest.setLimit(limit);
                    return Mono.just(pageRequest);
                }));
    }
}
