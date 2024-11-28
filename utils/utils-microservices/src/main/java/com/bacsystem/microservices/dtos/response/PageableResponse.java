package com.bacsystem.microservices.dtos.response;


import com.bacsystem.microservices.dtos.request.PageRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * <b>PageableResponse</b>
 * <p>
 * This class specifies the requirements for the {@link PageableResponse} component,
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
public class PageableResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -9009689089078346914L;

    @JsonProperty(value = "total")
    private Long total;
    @JsonProperty(value = "limit")
    private Integer limit;
    @JsonProperty(value = "currentPage")
    private Integer currentPage;
    @JsonProperty(value = "lastPage")
    private Integer lastPage;
    @JsonProperty(value = "nextPageUrl")
    private String nextPageUrl;
    @JsonProperty(value = "prevPageUrl")
    private String prevPageUrl;
    @JsonProperty(value = "firstPageUrl")
    private String firstPageUrl;
    @JsonProperty(value = "lastPageUrl")
    private String lastPageUrl;

    public static PageableResponse build(Page<?> page, PageRequest pageRequest) {
        int limit = page.getPageable().getPageSize();
        int lastPage = page.getTotalPages();
        long total = page.getTotalElements();

        var builder = builder()
                .total(total)
                .limit(limit)
                .currentPage(page.getNumber() + 1)
                .lastPage(lastPage)
                .firstPageUrl(getPageUrl(1, pageRequest))
                .lastPageUrl(getPageUrl(lastPage, pageRequest));

        if (page.hasNext()) {
            builder.nextPageUrl(getPageUrl(page.getNumber() + 2, pageRequest));
        }

        if (page.hasPrevious()) {
            builder.prevPageUrl(getPageUrl(page.getNumber(), pageRequest));
        }

        return builder.build();
    }

    private static String getPageUrl(Integer page, PageRequest pageRequest) {

        String pageUrl = String.format("?page=%s&limit=%s", page, pageRequest.getLimit());

        if (Objects.nonNull(pageRequest.getStartDate())) {
            pageUrl += String.format("&startDate=%s", pageRequest.getStartDate());
        }

        if (Objects.nonNull(pageRequest.getEndDate())) {
            pageUrl += String.format("&endDate=%s", pageRequest.getEndDate());
        }

        return pageUrl;
    }
}
