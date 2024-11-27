package com.bacsystem.upload.components.validators;


import com.bacsystem.microservices.dtos.request.PageRequest;
import reactor.core.publisher.Mono;

/**
 * <b>IValidatorRequest</b>
 * <p>
 * This class specifies the requirements for the {@link IValidatorRequest} component,
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

public interface IValidatorRequest {

    Mono<PageRequest> validateDataStatus(PageRequest pageRequest);
}
