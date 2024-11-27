package com.bacsystem.microservices.dtos.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <b>PageableGenericResponse</b>
 * <p>
 * This class specifies the requirements for the {@link PageableGenericResponse} component,
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
 * @since 26/11/2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageableGenericResponse<T,R> extends BaseResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -3492361656689867721L;
    @JsonProperty(value = "data")
    private T data;

    @JsonProperty(value = "metadata")
    private R metadata;
}
