package com.bacsystem.consumer.jobs;


import com.bacsystem.consumer.events.outbounds.IBrokerProducerTransaction;
import com.bacsystem.utils.dto.MQEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <b>ExecuteJob</b>
 * <p>
 * This class specifies the requirements for the {@link ExecuteJob} component,
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
public class ExecuteJob implements ApplicationListener<ApplicationReadyEvent> {

    private final IBrokerProducerTransaction brokerProducerTransaction;

    public ExecuteJob(IBrokerProducerTransaction brokerProducerTransaction) {
        this.brokerProducerTransaction = brokerProducerTransaction;
    }

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        log.info("start consumer");
        executeJob();
    }

    @Scheduled(cron = "0 */1 * * * *")
    private synchronized void executeJob() {
        log.info("consumer process execution at [{}]", LocalDateTime.now());
        MQEvent<String> event = brokerProducerTransaction.produceEventMessage("Hello World, soy el consumer");
        log.debug(event);
    }
}
