package com.bacsystem.connectors.soap.definitions;


import com.bacsystem.connectors.soap.configuration.MicroserviceSoapConfiguration;
import com.bacsystem.utils.components.factories.abstracts.contracts.ISoapCustomDefinition;
import com.bacsystem.utils.components.factories.abstracts.mappers.ISoapRequestMapper;
import com.bacsystem.utils.components.factories.abstracts.mappers.ISoapResponseMapper;
import com.bacsystem.utils.components.factories.abstracts.request.SoapRequest;
import com.bacsystem.utils.components.factories.abstracts.validations.ISoapRequestValidation;
import com.bacsystem.utils.components.factories.abstracts.validations.ISoapResponseValidation;
import org.springframework.stereotype.Component;

/**
 * CalculatorSoapCustomDefinition
 * <p>
 * CalculatorSoapCustomDefinition class.
 * <p>
 * This class specifies the requirements for the CalculatorSoapCustomDefinition component,
 * developed in accordance with the development standards established by bxcode.
 * Collaboration is encouraged for the enhancement and expansion of the api-microservice-application.
 *
 * @author bxcode
 * @author dbacilio88@outllok.es
 * @since 15/10/2024
 */

@Component
public class CalculatorSoapCustomDefinition implements ISoapCustomDefinition {

    private final MicroserviceSoapConfiguration microserviceSoapConfiguration;

    public CalculatorSoapCustomDefinition(MicroserviceSoapConfiguration microserviceSoapConfiguration) {
        this.microserviceSoapConfiguration = microserviceSoapConfiguration;
    }

    @Override
    public <S, T> ISoapRequestMapper<S, T> toSoapMapperRequest() {
        return null;
    }

    @Override
    public <S, T> ISoapResponseMapper<S, T> toSoapMapperResponse() {
        return null;
    }

    @Override
    public <S> ISoapRequestValidation<S> soapRequestValidation() {
        return null;
    }

    @Override
    public <T> ISoapResponseValidation<T> soapResponseValidation() {
        return null;
    }

    @Override
    public <S> SoapRequest<S> soapRequest(S request) {
        return null;
    }
}
