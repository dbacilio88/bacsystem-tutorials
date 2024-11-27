package com.bacsystem.upload.dtos.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <b>FileTypeResponse</b>
 * <p>
 * This class specifies the requirements for the {@link FileTypeResponse} component,
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileTypeResponse  implements Serializable {
    @Serial
    private static final long serialVersionUID = -7430825400794049297L;
    private String uuId;
    private String name;
    private String description;
    private String created;
}
