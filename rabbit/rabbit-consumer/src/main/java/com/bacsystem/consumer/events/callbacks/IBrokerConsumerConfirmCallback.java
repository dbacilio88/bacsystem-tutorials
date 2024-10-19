package com.bacsystem.consumer.events.callbacks;


import com.bacsystem.utils.components.annotations.MQConfirmCallBack;
import com.bacsystem.utils.dto.MQEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * <b>IBrokerConsumerConfirmCallback</b>
 * <p>
 * This class specifies the requirements for the {@link IBrokerConsumerConfirmCallback} component,
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
public class IBrokerConsumerConfirmCallback {

    @MQConfirmCallBack
   public  void confirmCallback(final MQEvent<String> event) {
        log.info("confirme consumer message {}", event.getMessage());
    }
}
