package com.bacsystem.upload.components.exceptions;


import com.bacsystem.microservices.components.enums.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * <b>ApplicationException</b>
 * <p>
 * This class specifies the requirements for the {@link ApplicationException} component,
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

@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationException extends RuntimeException {

    private ResponseCode responseCode;

    @Serial
    private static final long serialVersionUID = -4073601817720907622L;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(final String message, final ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }
}
