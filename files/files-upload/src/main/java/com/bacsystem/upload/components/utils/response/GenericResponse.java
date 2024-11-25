package com.bacsystem.upload.components.utils.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <b>GenericResponse</b>
 * <p>
 * This class specifies the requirements for the {@link GenericResponse} component,
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
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> extends ResponseBase implements Serializable {
    @Serial
    private static final long serialVersionUID = -5213182263894774561L;
    @JsonProperty(value = "data")
    private T data;
}
