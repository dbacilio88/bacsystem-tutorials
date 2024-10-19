package com.bacsystem.consumer.configuration;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <b>BrokerCommonConfiguration</b>
 * <p>
 * This class specifies the requirements for the {@link BrokerCommonConfiguration} component,
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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrokerCommonConfiguration implements Serializable {
    @Serial
    private static final long serialVersionUID = 2263794751196478827L;
    private String queueName;
    private String routingKeyOrigin;
    private String brokerDeclareExchange;
    private String brokerDeclareExchangeType;
}
