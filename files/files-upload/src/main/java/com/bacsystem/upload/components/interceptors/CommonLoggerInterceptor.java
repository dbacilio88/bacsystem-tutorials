package com.bacsystem.upload.components.interceptors;


import com.bacsystem.upload.components.constants.ApplicationConstant;
import lombok.experimental.UtilityClass;
import org.springframework.http.server.PathContainer;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Objects;
import java.util.function.Function;

/**
 * <b>interceptors</b>
 * <p>
 * This class specifies the requirements for the {@link interceptors} component,
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

@UtilityClass
public class CommonLoggerInterceptor {

    public static final Function<ServerWebExchange, Boolean> doOnValidateActuator = (
            exchange -> !Objects.isNull(exchange) && exchange.getRequest().getURI().getPath().contains(ApplicationConstant.MICROSERVICE_ACTUATOR_VALUE));

    public static final Function<ServerWebExchange, Boolean> doOnValidateHealthEndPoint = (exchange -> {
        if (Objects.isNull(exchange)) {
            return false;
        }

        var matches = PathContainer.parsePath(exchange.getRequest().getPath().pathWithinApplication().value());
        return new PathPatternParser().parse(ApplicationConstant.MICROSERVICE_ACTUATOR_PATH).matches(matches)
                || new PathPatternParser().parse(ApplicationConstant.MICROSERVICE_POD_INFO_PATH).matches(matches)
                || new PathPatternParser().parse(ApplicationConstant.MICROSERVICE_SWAGGER_UI_PATH).matches(matches)
                || new PathPatternParser().parse(ApplicationConstant.MICROSERVICE_SWAGGER_RESOURCES_PATH).matches(matches)
                || new PathPatternParser().parse(ApplicationConstant.MICROSERVICE_SWAGGER_V2_API_DOCS_PATH).matches(matches)
                || new PathPatternParser().parse(ApplicationConstant.MICROSERVICE_SWAGGER_V3_API_DOCS_PATH).matches(matches);

    });
}
