package com.bacsystem.consumer.events.outbounds;


import com.bacsystem.consumer.configuration.MicroserviceMessageConfiguration;
import com.bacsystem.consumer.helpers.RoutingKeyHelper;
import com.bacsystem.utils.components.annotations.MQBrokerProducer;
import com.bacsystem.utils.components.annotations.MQDeclareExchange;
import com.bacsystem.utils.components.enums.MQEventType;
import com.bacsystem.utils.dto.MQEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.bacsystem.consumer.constants.BrokerProcessConstants.BROKER_DECLARE_EXCHANGE;
import static com.bacsystem.consumer.constants.BrokerProcessConstants.BROKER_DECLARE_EXCHANGE_TYPE;

/**
 * <b>BrokerProducerTransaction</b>
 * <p>
 * This class specifies the requirements for the {@link BrokerProducerTransaction} component,
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
@Service
@MQDeclareExchange(exchanges = {BROKER_DECLARE_EXCHANGE}, type = BROKER_DECLARE_EXCHANGE_TYPE)
public class BrokerProducerTransaction implements IBrokerProducerTransaction {

    private final MicroserviceMessageConfiguration messageConfiguration;

    public BrokerProducerTransaction(MicroserviceMessageConfiguration messageConfiguration) {
        this.messageConfiguration = messageConfiguration;
    }

    @Override
    @MQBrokerProducer(exchange = BROKER_DECLARE_EXCHANGE)
    public MQEvent<String> produceEventMessage(String message) {
        return MQEvent.<String>builder()
                .message(message)
                .data(message)
                .routingKey(RoutingKeyHelper.createRoutingKey(MQEventType.SERVICE, messageConfiguration.getBrokerResponseProducer()))
                .build();
    }
}
