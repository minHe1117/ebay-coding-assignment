package ebay.coding.assignment.controller;

import ebay.coding.assignment.dto.common.ServiceRequest;
import ebay.coding.assignment.dto.common.ServiceResponse;
import ebay.coding.assignment.dto.MultipleOperationsRequest;
import ebay.coding.assignment.dto.SingleOperationRequest;
import ebay.coding.assignment.enums.Operation;
import ebay.coding.assignment.exceptions.ValidationException;
import ebay.coding.assignment.services.Calculator;
import ebay.coding.assignment.validators.CalculatorValidator;
import ebay.coding.assignment.validators.MultipleOperationsValidator;
import ebay.coding.assignment.validators.SingleOperationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ebay.coding.assignment.services.MultipleCalculator;
import ebay.coding.assignment.utils.NumberUtils;

@RestController
@RequestMapping("v1/calculator")
public class CalculatorController {

    private static final Logger log = LoggerFactory.getLogger(NumberUtils.class);

    @Autowired
    private CalculatorValidator calculatorValidator;
    @Autowired
    private SingleOperationValidator singleOperationValidator;
    @Autowired
    private MultipleOperationsValidator multipleOperationsValidator;

    @Autowired
    private MultipleCalculator multipleCalculator;
    @Autowired
    private Calculator calculator;

    @PostMapping("/calculate")
    public ServiceResponse<Number> processSingleOperation(@RequestBody ServiceRequest<SingleOperationRequest> serviceRequest) {
        log.info("Received request to process single operation");
        try {
            // Validate the request using the CalculatorValidator
            calculatorValidator.validate(serviceRequest);
            SingleOperationRequest request = serviceRequest.getPayload();
            Operation operation = singleOperationValidator.validate(request);
            Number result = calculator.calculate(operation, request.getNum1(), request.getNum2());

            log.info("Single operation processed successfully. Operation: {}, Result: {}", operation, result);
            return ServiceResponse.success(result);
        } catch (ValidationException e) {
            log.error("Validation error: {}", e.getMessage());
            return ServiceResponse.error("400", e.getMessage());
        } catch (Exception e) {
            log.error("Error processing request: {}", e.getMessage(), e);
            return ServiceResponse.error("500", e.getMessage());
        }
    }

    @PostMapping("/chain")
    public ServiceResponse<Number> processChainOperation(@RequestBody ServiceRequest<MultipleOperationsRequest> serviceRequest) {
        try {
            // Validate the request using the CalculatorValidator
            calculatorValidator.validate(serviceRequest);
            MultipleOperationsRequest request = serviceRequest.getPayload();

            // Validate the ChainRequest using the ChainOperationValidator
            multipleOperationsValidator.validate(request);
            Number result = multipleCalculator.processChainOperations(request.getInitialValue(), request.getChainOperations());

            log.info("Multiple operations processed successfully. Final result: {}", result);
            return ServiceResponse.success(result);
        } catch (ValidationException e) {
            log.error("Validation error: {}", e.getMessage());
            return ServiceResponse.error("400", e.getMessage());
        } catch (Exception e) {
            log.error("Error processing request: {}", e.getMessage(), e);
            return ServiceResponse.error("500", e.getMessage());
        }
    }

}
