package com.bacsystem.upload.dtos.request;


import com.bacsystem.microservices.dtos.request.PageRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * <b>PageStatusRequest</b>
 * <p>
 * This class specifies the requirements for the {@link PageStatusRequest} component,
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageStatusRequest extends PageRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -8497386946471330298L;
    private String uuId;

}