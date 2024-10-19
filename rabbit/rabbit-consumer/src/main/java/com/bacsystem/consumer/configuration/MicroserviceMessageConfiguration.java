package com.bacsystem.consumer.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <b>MicroserviceMessageConfiguration</b>
 * <p>
 * This class specifies the requirements for the {@link MicroserviceMessageConfiguration} component,
 * developed in accordance with the development standards established by bxcode.
 * Collaboration is encouraged for the enhancement and expansion of the bacsystem-tutorials.
 * </p>
 * <p>
 * <b>Usage:</b>
 * description here!
 * </p>
 *
 * @author bxcode
 * @author dbacilio88@outllok.es
 * @since 18/10/2024
 */

@EnableScheduling
@Data
@Configuration
@ConfigurationProperties(prefix = "broker-configuration")
public class MicroserviceMessageConfiguration {
    private BrokerCommonConfiguration commonConfiguration;
    private BrokerConfiguration brokerResponseProducer;
    private BrokerConfiguration brokerRequestConsumer;
}