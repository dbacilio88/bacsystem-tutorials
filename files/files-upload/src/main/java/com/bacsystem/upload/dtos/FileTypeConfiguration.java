package com.bacsystem.upload.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <b>FileTypeConfiguration</b>
 * <p>
 * This class specifies the requirements for the {@link FileTypeConfiguration} component,
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
public class FileTypeConfiguration implements Serializable {
    @Serial
    private static final long serialVersionUID = 115442935258763928L;

    private String fileType;
    private String fileNamePattern;
    private String tableName;
    private Integer batchSize;
    private List<FileTypeFieldConfiguration> fields;


}
