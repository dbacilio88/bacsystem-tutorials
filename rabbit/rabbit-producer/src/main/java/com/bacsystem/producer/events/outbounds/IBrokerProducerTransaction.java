package com.bacsystem.producer.events.outbounds;


import com.bacsystem.utils.dto.MQEvent;

/**
 * <b>IBrokerProducerTransaction</b>
 * <p>
 * This class specifies the requirements for the {@link IBrokerProducerTransaction} component,
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

public interface IBrokerProducerTransaction {

    MQEvent<String> produceEventMessage(String message);
}
