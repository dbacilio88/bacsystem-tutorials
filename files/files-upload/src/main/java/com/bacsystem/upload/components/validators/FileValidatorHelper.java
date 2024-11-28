package com.bacsystem.upload.components.validators;


import com.bacsystem.microservices.components.enums.ResponseCode;
import com.bacsystem.upload.components.exceptions.ApplicationException;
import com.bacsystem.upload.components.helpers.FileHelper;
import com.bacsystem.upload.components.helpers.FileZip;
import com.bacsystem.upload.dtos.FileTypeConfiguration;
import com.bacsystem.upload.repositories.IDataFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static io.micrometer.common.util.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * <b>FileValidatorHelper</b>
 * <p>
 * This class specifies the requirements for the {@link FileValidatorHelper} component,
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
 * @since 27/11/2024
 */

@Log4j2
@Component
@RequiredArgsConstructor
public class FileValidatorHelper {

    private final IDataFileRepository dataFileRepository;

    public Mono<FileZip> doOnValidateFile(final String fileName,
                                          final DataBuffer dataBuffer,
                                          final FileTypeConfiguration fileTypeConfiguration) {

        return doOnValidateName(fileName,fileTypeConfiguration)
                .then(doOnExistFileType(fileName,fileTypeConfiguration.getFileType()))
                .then(doOnValidateHeaderRecord(fileName,fileTypeConfiguration))
                .thenReturn(FileZip.builder()
                        .fileName(fileName)
                        .buffer(dataBuffer)
                        .build());

    }


    private Mono<Void>doOnValidateHeaderRecordsFile(final String fileName,final DataBuffer dataBuffer,final FileTypeConfiguration fileTypeConfiguration) {
        return doOnGetDataBuffer(dataBuffer)
                .flatMap(reader-> {
                    try {
                        return Mono.just(reader.readLine());
                    } catch (IOException e) {
                        final String message = String.format("error reading first line from file [%s]",fileName);
                       return Mono.error(new ApplicationException(message,ResponseCode.NOT_FOUND));
                    }
                })
                .flatMap(firstLine-> doOnValidateHeaderRecord(firstLine,fileTypeConfiguration)
                        .flatMap(result->{
                            DataBufferUtils.release(dataBuffer);
                            //todo validate
                            return Mono.empty();
                        }));
    }

    public static Mono<Boolean> doOnValidateHeaderRecord(final String headerRecord,final FileTypeConfiguration fileTypeConfiguration) {
        return Mono.just(isBlank(headerRecord))
                .filter(result-> !result)
                .flatMap(result-> Mono.just(
                        Objects.isNull(fileTypeConfiguration)||
                                Objects.isNull(fileTypeConfiguration.getFields())||
                                fileTypeConfiguration.getFields().isEmpty())
                        .flatMap(Mono::just))
                .flatMap(result->Mono.just(split(trimToEmpty(headerRecord).replace("\"","")))
                        .map(str-> str.length!= fileTypeConfiguration.getFields().size())
                        .flatMap(Mono::just))
                // todo list
                .switchIfEmpty(Mono.just(false));
    }

    private Mono<BufferedReader>doOnGetDataBuffer(final DataBuffer dataBuffer){
        return Mono.fromCallable(() -> new BufferedReader(new InputStreamReader(dataBuffer.asInputStream(), StandardCharsets.UTF_8)));
    }

    private Mono<Boolean>doOnExistFileType(final String fileName,   final String fileType) {
        return Mono.fromCallable(() -> true)
                .flatMap(exist-> Boolean.TRUE.equals(exist)
                        ? Mono.error(new ApplicationException(String.format("file [%s] already exists for file type [%s] ",fileName,fileType),ResponseCode.NOT_FOUND))
                        : Mono.just(exist));
    }

    private Mono<Void>doOnValidateName(final String fileName,final FileTypeConfiguration fileTypeConfiguration) {
        return FileHelper.doOnValidateFileName(fileName,fileTypeConfiguration)
                .filter(result-> !result)
                .flatMap(result-> {
                    final String message = String.format("invalid file name. Expected [%s], actual [%s]",fileTypeConfiguration.getFileNamePattern(),fileName);
                    return Mono.error(new ApplicationException(message, ResponseCode.NOT_FOUND));
                })
                .switchIfEmpty(Mono.just(Mono.empty()))
                .then();
    }

}
