package com.bacsystem.microservices.dtos.response;


import com.bacsystem.microservices.components.enums.ResponseCode;
import com.bacsystem.microservices.components.enums.ResponseResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * <b>ProcessResponse</b>
 * <p>
 * This class specifies the requirements for the {@link ProcessResponse} component,
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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessResponse {

    private ResponseCode responseCode;
    private BaseResponse response;
    private boolean success;
    private boolean empty;
    private ResponseResult result;

    public static ProcessResponse success(final BaseResponse response) {
        return ProcessResponse
                .builder()
                .responseCode(ResponseCode.SUCCESS)
                .response(response)
                .success(true)
                .empty(false)
                .result(ResponseResult.SUCCESS)
                .build();
    }

    public static ProcessResponse empty(final BaseResponse response) {
        return ProcessResponse
                .builder()
                .responseCode(ResponseCode.SUCCESS)
                .response(response)
                .success(true)
                .empty(true)
                .result(ResponseResult.SUCCESS)
                .build();
    }


    public static ProcessResponse failure(final ResponseCode responseCode) {
        return ProcessResponse
                .builder()
                .responseCode(responseCode)
                .response(null)
                .success(false)
                .empty(false)
                .result(ResponseResult.FAILURE)
                .build();
    }

    public static ProcessResponse failure(final BaseResponse response, final ResponseCode responseCode) {
        return ProcessResponse
                .builder()
                .responseCode(responseCode)
                .response(response)
                .success(false)
                .empty(false)
                .result(ResponseResult.FAILURE)
                .build();
    }

    public boolean isError() {
        return !success;
    }

    public boolean exists() {
        return !Objects.isNull(response);
    }
}