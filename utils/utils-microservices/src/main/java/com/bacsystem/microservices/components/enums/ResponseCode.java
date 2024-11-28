package com.bacsystem.microservices.components.enums;


import lombok.Getter;

/**
 * <b>ResponseCode</b>
 * <p>
 * This class specifies the requirements for the {@link ResponseCode} component,
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

@Getter
public enum ResponseCode {
    SUCCESS(200),
    NOT_FOUND(404);
    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }
}