package com.bacsystem.upload.components.helpers;


import com.bacsystem.microservices.components.enums.ResponseCode;
import com.bacsystem.upload.components.exceptions.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * <b>FileZipHelper</b>
 * <p>
 * This class specifies the requirements for the {@link FileZipHelper} component,
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

@Log4j2
@Component
public class FileZipHelper {

    /**
     * Validates the ZIP file and attempts to unzip its content.
     *
     * @param dataBuffer The data buffer containing the zip file content.
     * @return A Mono emitting a FileZip object representing the extracted file.
     * @throws ApplicationException if the ZIP is invalid or does not meet the criteria.
     */
    public Mono<FileZip> doOnUnZip(final DataBuffer dataBuffer){
        return doOnIsInvalidZipOneFile(dataBuffer)
                .flatMap(isValid-> doOnUnZip(dataBuffer));
    }

    /**
     * Extracts a single file from the given zip file.
     * The method assumes the ZIP contains exactly one file and processes it.
     *
     * @param dataBuffer The data buffer containing the zip file content.
     * @return A Flux emitting FileZip objects, each representing a file in the zip.
     */
    private Flux<FileZip> doOnZipOneFile(final DataBuffer dataBuffer){
        return Mono.fromCallable(()-> {
                    try (ZipInputStream zipInputStream = new ZipInputStream(dataBuffer.asInputStream())) {
                        ZipEntry zipEntry;
                        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                            if (!zipEntry.isDirectory()) {
                                final String fileName = zipEntry.getName();
                                final DataBuffer buffer = dataBuffer.factory().allocateBuffer(1024);
                                int bytesRead;
                                byte[] bytes = new byte[buffer.capacity()];
                                while ((bytesRead = zipInputStream.read(bytes)) != -1) {
                                    buffer.write(bytes, 0, bytesRead);
                                }
                                return FileZip.builder().fileName(fileName).buffer(buffer).build();
                            }
                        }
                    } catch (IOException e) {
                        log.error("Error unzipping file [{}]", e.getMessage());
                        throw new ApplicationException("Error unzipping file", ResponseCode.NOT_FOUND);
                    }
                    return null;
                })
                .flatMapMany(fileZip -> fileZip!=null? Flux.just(fileZip): Flux.empty())
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * Validates that the provided zip file contains exactly one file (and not a directory).
     *
     * @param dataBuffer The data buffer containing the zip file content.
     * @return A Mono emitting a Boolean indicating if the file is valid.
     * @throws ApplicationException if the zip file is invalid (e.g., empty or contains more than one file).
     */
    private Mono<Boolean> doOnIsInvalidZipOneFile(final DataBuffer dataBuffer){
        return Mono.fromCallable(()->{
           try(ZipInputStream zipInputStream = new ZipInputStream(dataBuffer.asInputStream())){
               ZipEntry zipEntry;
               int fileCount = 0;
               while((zipEntry = zipInputStream.getNextEntry())!=null){
                   if(zipEntry.isDirectory()){
                       fileCount++;
                   }else {
                       return -1;
                   }
               }
               log.info("[{}] files found in zip", fileCount);
               return fileCount;
           }catch (IOException e){
               return 0;
           }
        }).flatMap(count-> {
            if (count!=1){
                return Mono.error(()-> new ApplicationException("Invalid zip file", ResponseCode.NOT_FOUND));
            }
            return Mono.just(Boolean.TRUE);
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
