package com.bacsystem.upload.dtos.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;

import java.io.Serial;
import java.io.Serializable;

/**
 * <b>FileUploadRequest</b>
 * <p>
 * This class specifies the requirements for the {@link FileUploadRequest} component,
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileUploadRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 3636669107961515236L;

    private String type;
    private FilePart file;
}
