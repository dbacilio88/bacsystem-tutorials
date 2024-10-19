package com.bacsystem.producer.events.inbounds;


import com.bacsystem.utils.components.annotations.MQBrokerConsumer;
import com.bacsystem.utils.components.annotations.MQDeclareBinding;
import com.bacsystem.utils.components.annotations.MQDeclareExchange;
import com.bacsystem.utils.components.annotations.MQDeclareQueue;
import com.bacsystem.utils.dto.MQEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.bacsystem.producer.constants.BrokerProcessConstants.*;

/**
 * <b>BrokerConsumerTransaction</b>
 * <p>
 * This class specifies the requirements for the {@link BrokerConsumerTransaction} component,
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
@Component
@MQDeclareExchange(exchanges = {BROKER_DECLARE_EXCHANGE}, type = BROKER_DECLARE_EXCHANGE_TYPE)
@MQDeclareBinding(exchange = BROKER_DECLARE_EXCHANGE,
        routingKey = BROKER_RABBIT_CONSUMER_TRANSACTION_RESPONSE_CONSUMER_ROUTING_KEY,
        queue = BROKER_RABBIT_CONSUMER_TRANSACTION_RESPONSE_QUEUE)
public class BrokerConsumerTransaction implements IBrokerConsumerTransaction {


    @Override
    @MQBrokerConsumer(queues = BROKER_RABBIT_CONSUMER_TRANSACTION_RESPONSE_QUEUE,
            declareBindings = @MQDeclareBinding(exchange = BROKER_DECLARE_EXCHANGE, queue = BROKER_RABBIT_CONSUMER_TRANSACTION_RESPONSE_QUEUE,
                    routingKey = BROKER_RABBIT_CONSUMER_TRANSACTION_RESPONSE_CONSUMER_ROUTING_KEY),
            declareQueues = @MQDeclareQueue(queues = BROKER_RABBIT_CONSUMER_TRANSACTION_RESPONSE_QUEUE))
    public void consumeEventMessage(MQEvent<String> message) {
        log.info("producer inbound {}", message.getMessage());
    }
}
