package com.bacsystem.connectors.soap.definitions;

import com.bacsystem.connectors.soap.configuration.MicroserviceSoapConfiguration;
import com.bacsystem.connectors.wsdl.calculator.Add;
import com.bacsystem.connectors.wsdl.calculator.AddResponse;
import com.bacsystem.utils.components.factories.abstracts.contracts.ISoapStandardDefinition;
import com.bacsystem.utils.components.factories.abstracts.request.SoapRequest;
import com.bacsystem.utils.components.factories.abstracts.validations.ISoapRequestValidation;
import com.bacsystem.utils.components.factories.abstracts.validations.ISoapResponseValidation;
import org.springframework.stereotype.Component;

import static com.bacsystem.utils.components.constants.CommonSoapConstants.SOAP_SUPPRESS_WARNINGS_UNCHECKED;

/**
 * CalculatorSoapStandardDefinition
 * <p>
 * CalculatorSoapStandardDefinition class.
 * <p>
 * This class specifies the requirements for the CalculatorSoapStandardDefinition component,
 * developed in accordance with the development standards established by bxcode.
 * Collaboration is encouraged for the enhancement and expansion of the api-microservice-application.
 *
 * @author bxcode
 * @author dbacilio88@outllok.es
 * @since 15/10/2024
 */
@SuppressWarnings(SOAP_SUPPRESS_WARNINGS_UNCHECKED)
@Component("SOAP_STANDARD_DEFINITION_CALCULATOR")
public class CalculatorSoapStandardDefinition implements ISoapStandardDefinition {

    private final MicroserviceSoapConfiguration microserviceSoapConfiguration;

    private final ISoapRequestValidation<Add> soapRequestValidation;

    private final ISoapResponseValidation<AddResponse> soapResponseValidation;

    public CalculatorSoapStandardDefinition(
            final MicroserviceSoapConfiguration microserviceSoapConfiguration,
            final ISoapRequestValidation<Add> soapRequestValidation,
            final ISoapResponseValidation<AddResponse> soapResponseValidation) {
        this.microserviceSoapConfiguration = microserviceSoapConfiguration;

        this.soapRequestValidation = soapRequestValidation;
        this.soapResponseValidation = soapResponseValidation;
    }

    @Override
    public <SQ> ISoapRequestValidation<SQ> soapRequestValidation() {
        return (ISoapRequestValidation<SQ>) soapRequestValidation;
    }

    @Override
    public <SR> ISoapResponseValidation<SR> soapResponseValidation() {
        return (ISoapResponseValidation<SR>) soapResponseValidation;
    }

    @Override
    public <SQ> SoapRequest<SQ> soapRequest(SQ request) {
        return (SoapRequest<SQ>) SoapRequest.<Add>builder()
                .request((Add) request)
                .parameter(microserviceSoapConfiguration.getParameter())
                .build();
    }
}
