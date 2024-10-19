package com.bacsystem.producer.configuration;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <b>BrokerConfiguration</b>
 * <p>
 * This class specifies the requirements for the {@link BrokerConfiguration} component,
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
public class BrokerConfiguration implements Serializable {
    @Serial
    private static final long serialVersionUID = -5403009918812704030L;
    private String queue;
    private String command;
    private String routingKey;
    private String routingDomain;
    private String routingKeyOrigin;
    private String routingKeyDestiny;
}
