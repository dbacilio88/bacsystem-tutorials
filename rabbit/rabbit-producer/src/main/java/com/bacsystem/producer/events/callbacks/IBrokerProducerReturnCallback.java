package com.bacsystem.producer.events.callbacks;


import com.bacsystem.utils.components.annotations.MQReturnCallBack;
import com.bacsystem.utils.dto.MQEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * <b>IBrokerProducerReturnCallback</b>
 * <p>
 * This class specifies the requirements for the {@link IBrokerProducerReturnCallback} component,
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
@Component
@Log4j2
public class IBrokerProducerReturnCallback {
    @MQReturnCallBack
    public void returnCallback(int code, String message, MQEvent<String> event) {
        log.info("[producer]: return callback code: {}, message: {}, event:{}", code, message, event);
    }
}
