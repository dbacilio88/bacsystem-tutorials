package com.bacsystem.consumer.helpers;


import com.bacsystem.consumer.configuration.BrokerConfiguration;
import com.bacsystem.utils.components.enums.MQEventType;
import com.bacsystem.utils.components.exceptions.MQBrokerException;
import com.bacsystem.utils.dto.MQRoutingKey;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

/**
 * <b>RoutingKeyHelper</b>
 * <p>
 * This class specifies the requirements for the {@link RoutingKeyHelper} component,
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
@Log4j2
@UtilityClass
public class RoutingKeyHelper {
    public static MQRoutingKey createRoutingKey(final MQEventType eventType, final BrokerConfiguration brokerConfiguration) {
        if (Objects.isNull(brokerConfiguration)) {
            throw new MQBrokerException("error in brokerConfiguration, the current configuration is null");
        }
        final MQRoutingKey routingKey = MQRoutingKey.builder()
                .eventType(eventType)
                .origin(brokerConfiguration.getRoutingKeyOrigin())
                .destiny(brokerConfiguration.getRoutingKeyDestiny())
                .domain(brokerConfiguration.getRoutingDomain())
                .command(brokerConfiguration.getCommand())
                .build();
        log.debug("the routingKey configuration is {}", routingKey.toString());
        return routingKey;
    }
}
