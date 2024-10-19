package com.bacsystem.producer.constants;


import lombok.experimental.UtilityClass;

/**
 * <b>BrokerProcessConstants</b>
 * <p>
 * This class specifies the requirements for the {@link BrokerProcessConstants} component,
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

@UtilityClass
public class BrokerProcessConstants {
    public static final String BROKER_CONFIGURATION_PREFIX = "broker-configuration";
    public static final String BROKER_DECLARE_EXCHANGE = "${broker-configuration.common-configuration.broker-declare-exchange}";
    public static final String BROKER_DECLARE_EXCHANGE_TYPE = "${broker-configuration.common-configuration.broker-declare-exchange-type}";
    public static final String BROKER_RABBIT_CONSUMER_TRANSACTION_RESPONSE_QUEUE = "${broker-configuration.broker-response-consumer.queue}";
    public static final String BROKER_RABBIT_CONSUMER_TRANSACTION_RESPONSE_CONSUMER_ROUTING_KEY = "${broker-configuration.broker-response-consumer.routing-key}";
}