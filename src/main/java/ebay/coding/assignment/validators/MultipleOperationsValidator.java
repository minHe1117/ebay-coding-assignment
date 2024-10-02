package ebay.coding.assignment.validators;

import ebay.coding.assignment.dto.MultipleOperationsRequest;
import ebay.coding.assignment.dto.OperationRequest;
import ebay.coding.assignment.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Validator for validating ChainRequest.
 * Ensures that the initial value and list of operations are valid.
 */
@Component
public class MultipleOperationsValidator {

    private static final Logger LOG = LoggerFactory.getLogger(MultipleOperationsValidator.class);

    /**
     * Validates the ChainRequest, ensuring it has valid initial value and operations.
     *
     * @param request the ChainRequest containing the initial value and the list of operations.
     * @throws ValidationException if the request contains invalid data.
     */
    public void validate(MultipleOperationsRequest request) {
        if (request.getInitialValue() == null) {
            LOG.error("Validation failed: Initial value is null");
            throw new ValidationException("Initial value cannot be null");
        }

        List<OperationRequest> operations = request.getChainOperations();
        if (operations == null || operations.isEmpty()) {
            LOG.error("Validation failed: Operations list is null or empty");
            throw new ValidationException("Operations list cannot be null or empty");
        }

        for (OperationRequest operationRequest : operations) {
            if (operationRequest.getOp() == null || operationRequest.getOp().trim().isEmpty()) {
                LOG.error("Validation failed: Operation string is null or empty");
                throw new ValidationException("Operation cannot be null or empty");
            }
            if (operationRequest.getNum() == null) {
                LOG.error("Validation failed: Operation number is null");
                throw new ValidationException("Operation number cannot be null");
            }
        }
    }
}