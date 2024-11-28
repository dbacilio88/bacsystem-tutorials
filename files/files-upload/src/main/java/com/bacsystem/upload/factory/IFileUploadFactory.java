package com.bacsystem.upload.factory;


import reactor.core.publisher.Mono;

/**
 * <b>IFileUploadFactory</b>
 * <p>
 * This class specifies the requirements for the {@link IFileUploadFactory} component,
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
 * @since 23/11/2024
 */

public interface IFileUploadFactory {

    Mono<FileUploadAbstract> factory(final String type);
}
