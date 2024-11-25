package com.bacsystem.upload.components.utils.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <b>ResponseBase</b>
 * <p>
 * This class specifies the requirements for the {@link ResponseBase} component,
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
@ToString
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBase implements Serializable {
    @Serial
    private static final long serialVersionUID = -8852234456287436592L;
    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "dateTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date dateTime;

    @JsonProperty(value = "errors")
    private List<String> errors;

    public void setDateTime(Date date) {
        this.dateTime = Optional
                .ofNullable(date)
                .map(Date::getTime)
                .map(Date::new)
                .orElse(null);
    }

    public Date getDateTime() {
        this.dateTime = Optional
                .ofNullable(this.dateTime)
                .map(Date::getTime)
                .map(Date::new)
                .orElse(null);
        return this.dateTime;
    }
}
