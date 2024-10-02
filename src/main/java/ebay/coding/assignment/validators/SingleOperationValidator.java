package ebay.coding.assignment.validators;

import ebay.coding.assignment.dto.SingleOperationRequest;
import ebay.coding.assignment.enums.Operation;
import ebay.coding.assignment.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Validator class for validating single operation requests.
 * This class ensures that the operation and input numbers are valid.
 */
@Component
public class SingleOperationValidator {

    private static final Logger LOG = LoggerFactory.getLogger(CalculatorValidator.class);

    /**
     * @param request the SingleOperationRequest containing the operation and input numbers.
     * @throws ValidationException if the request contains invalid data.
     */

    public Operation validate(SingleOperationRequest request){

        if (request.getNum1() == null || request.getNum2() == null) {
            LOG.error("Validation failed: One or both numbers are null");
            throw new ValidationException("Number cannot be null");
        }
        return validateAndParseOperation(request.getOp());
    }

    /**
     * @param operation the operation string to validate and parse.
     * @return the parsed Operation enum.
     * @throws ValidationException if the operation is invalid.
     */
    private Operation validateAndParseOperation(String operation) {
        if (operation == null || operation.trim().isEmpty()) {
            LOG.error("Operation string is null or empty");
            throw new ValidationException("Operation cannot be null or empty");
        }

        try {
            return Operation.valueOf(operation.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            LOG.error("Invalid operation");
            throw new ValidationException("Invalid operation");
        }
    }

}
