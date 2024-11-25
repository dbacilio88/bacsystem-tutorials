package com.bacsystem.upload.factory;


import reactor.core.publisher.Mono;

/**
 * <b>IFileFactory</b>
 * <p>
 * This class specifies the requirements for the {@link IFileFactory} component,
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

public interface IFileFactory {

    Mono<FileUploadAbstract> fileFactory(final String type);
}
