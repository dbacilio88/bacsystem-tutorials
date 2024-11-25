package com.bacsystem.upload.configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * <b>SwaggerConfiguration</b>
 * <p>
 * This class specifies the requirements for the {@link SwaggerConfiguration} component,
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

@Configurable
@OpenAPIDefinition(
        info = @Info(
                title = "api-files-upload",
                version = "1.0.0",
                description = "api-files-upload"
        )
)
public class SwaggerConfiguration {
}
