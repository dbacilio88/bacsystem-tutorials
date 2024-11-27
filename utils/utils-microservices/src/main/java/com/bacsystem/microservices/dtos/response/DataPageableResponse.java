package com.bacsystem.microservices.dtos.response;


import com.bacsystem.microservices.dtos.request.PageRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;

/**
 * <b>DataPageableResponse</b>
 * <p>
 * This class specifies the requirements for the {@link DataPageableResponse} component,
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataPageableResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -9179870591310562781L;
    @JsonProperty(value = "pageable")
    private PageableResponse pageable;

    public static DataPageableResponse build(Page<?> page, final PageRequest pageRequest) {
       return builder()
               .pageable(PageableResponse.build(page,pageRequest))
               .build();
    }
}
