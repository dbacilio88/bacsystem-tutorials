package com.bacsystem.connectors.soap.validations;

import com.bacsystem.connectors.wsdl.calculator.AddResponse;
import com.bacsystem.utils.components.enums.ValidateResult;
import com.bacsystem.utils.components.factories.SoapValidationResult;
import com.bacsystem.utils.components.factories.abstracts.validations.ISoapResponseValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * CalculatorSoapResponseValidation
 * <p>
 * CalculatorSoapResponseValidation class.
 * <p>
 * This class specifies the requirements for the CalculatorSoapResponseValidation component,
 * developed in accordance with the development standards established by bxcode.
 * Collaboration is encouraged for the enhancement and expansion of the api-microservice-application.
 *
 * @author bxcode
 * @author dbacilio88@outllok.es
 * @since 15/10/2024
 */
@Log4j2
@Component
public class CalculatorSoapResponseValidation implements ISoapResponseValidation<AddResponse> {
    @Override
    public SoapValidationResult validateResponse(AddResponse response) {
        return SoapValidationResult.builder().validateResult(ValidateResult.PROCESS_VALIDATION_SUCCESS).build();
    }
}
