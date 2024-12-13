package com.bacsystem.upload.factory;


import com.bacsystem.microservices.components.enums.ResponseCode;
import com.bacsystem.upload.components.exceptions.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * <b>FileUploadFactory</b>
 * <p>
 * This class specifies the requirements for the {@link FileUploadFactory} component,
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
@Log4j2
@Component
public class FileUploadFactory implements IFileUploadFactory {

    private final BeanFactory beanFactory;

    public FileUploadFactory(final BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Mono<FileUploadAbstract> factory(String type) {
        return Mono.fromCallable(() -> beanFactory.getBean(type,FileUploadAbstract.class))
                .doOnError(error -> log.error("error in get service, error {}", error.getMessage()))
                .onErrorResume(BeansException.class, e-> Mono.error(new ApplicationException(String.format("file type [%s] not found",type), ResponseCode.NOT_FOUND)));
    }
}
