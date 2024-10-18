package com.bacsystem.connectors.soap.validations;

import com.bacsystem.connectors.wsdl.calculator.Add;
import com.bacsystem.utils.components.enums.ValidateResult;
import com.bacsystem.utils.components.factories.SoapValidationResult;
import com.bacsystem.utils.components.factories.abstracts.validations.ISoapRequestValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * CalculatorSoapRequestValidation
 * <p>
 * CalculatorSoapRequestValidation class.
 * <p>
 * This class specifies the requirements for the CalculatorSoapRequestValidation component,
 * developed in accordance with the development standards established by bxcode.
 * Collaboration is encouraged for the enhancement and expansion of the api-microservice-application.
 *
 * @author bxcode
 * @author dbacilio88@outllok.es
 * @since 15/10/2024
 */
@Log4j2
@Component
public class CalculatorSoapRequestValidation implements ISoapRequestValidation<Add> {

    @Override
    public SoapValidationResult validateRequest(Add request) {

        final SoapValidationResult result = SoapValidationResult.builder().validateResult(ValidateResult.PROCESS_VALIDATION_SUCCESS).build();
        final List<String> errors = new ArrayList<>();
        if (request.getIntA() < 0) {
            log.error("error the request int A");
            errors.add("error the request int A");
        }
        if (request.getIntB() < 0) {
            log.error("error the request int B");
            errors.add("error the request int B");
        }
        if (!errors.isEmpty()) {
            result.setValidateResult(ValidateResult.PROCESS_VALIDATION_ERROR);
            result.setErrors(errors);
        }

        return result;
    }
}
