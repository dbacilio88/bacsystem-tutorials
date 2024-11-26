package com.bacsystem.upload.components.exceptions.handlers;


import com.bacsystem.microservices.components.exceptions.MicroservicesException;
import com.bacsystem.microservices.dtos.response.ApplicationResponse;
import com.bacsystem.microservices.dtos.response.BaseResponse;
import com.bacsystem.upload.components.exceptions.ApplicationException;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * <b>ApplicationGlobalHandler</b>
 * <p>
 * This class specifies the requirements for the {@link ApplicationGlobalHandler} component,
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
 * @since 24/11/2024
 */

@Log4j2
@RestControllerAdvice
@AllArgsConstructor
public class ApplicationGlobalHandler {

    private final ApplicationResponse applicationResponse;

    @ExceptionHandler(MicroservicesException.class)
    public Mono<ResponseEntity<Object>> handlerMicroservice(MicroservicesException exception) {
        return applicationResponse.getResponseError(
                BaseResponse.builder()
                        .code(exception.getResponseCode().getCode())
                        .message(exception.getMessage())
                        .build(), exception.getResponseCode().getCode()
        );
    }

    @ExceptionHandler(ApplicationException.class)
    public Mono<ResponseEntity<Object>> handlerException(ApplicationException exception) {
        return applicationResponse.getResponseError(
                BaseResponse.builder()
                        .code(exception.getResponseCode().getCode())
                        .message(exception.getMessage())
                        .build(), exception.getResponseCode().getCode()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Mono<ResponseEntity<Object>> constraintViolationException(ConstraintViolationException exception) {
        return applicationResponse.getResponseError(
                BaseResponse.builder()
                        .code(400)
                        .errors(exception
                                .getConstraintViolations()
                                .stream()
                                .map(constraint -> String.format("Field %s: %s", constraint.getPropertyPath(), constraint.getMessage()))
                                .toList())
                        .message("Parameter invalid")
                        .build(), 400);
    }
}