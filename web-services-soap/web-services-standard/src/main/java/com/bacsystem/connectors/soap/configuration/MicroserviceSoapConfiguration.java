package com.bacsystem.connectors.soap.configuration;


import com.bacsystem.utils.parameters.SoapParameter;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * MicroserviceSoapConfiguration
 * <p>
 * MicroserviceSoapConfiguration class.
 * <p>
 * This class specifies the requirements for the MicroserviceSoapConfiguration component,
 * developed in accordance with the development standards established by bxcode.
 * Collaboration is encouraged for the enhancement and expansion of the api-microservice-application.
 *
 * @author bxcode
 * @author dbacilio88@outllok.es
 * @since 15/10/2024
 */
@Log4j2
@Data
@Configuration
@ConfigurationProperties(prefix = "soap-integration-events")
public class MicroserviceSoapConfiguration {

    private SoapParameter parameter;
}
