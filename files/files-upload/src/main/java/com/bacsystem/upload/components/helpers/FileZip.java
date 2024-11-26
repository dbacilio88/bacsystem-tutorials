package com.bacsystem.upload.components.helpers;


import lombok.*;
import org.springframework.core.io.buffer.DataBuffer;

import java.io.Serial;
import java.io.Serializable;

/**
 * <b>FileZip</b>
 * <p>
 * This class specifies the requirements for the {@link FileZip} component,
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
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileZip implements Serializable {
    @Serial
    private static final long serialVersionUID = -9025099275811846964L;
    private String fileName;
    private String filePath;
    private String fileType;
    private String fileSize;
    private String fileExtension;
    private DataBuffer buffer;
}
