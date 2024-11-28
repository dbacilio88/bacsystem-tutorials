package com.bacsystem.microservices.dtos.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <b>PageRequest</b>
 * <p>
 * This class specifies the requirements for the {@link PageRequest} component,
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -6028826523949746354L;
    private Integer page;
    private Integer limit;
    private LocalDate startDate;
    private LocalDate endDate;
}