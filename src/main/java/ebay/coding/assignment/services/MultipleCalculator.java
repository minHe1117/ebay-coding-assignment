package ebay.coding.assignment.services;

import ebay.coding.assignment.dto.OperationRequest;
import ebay.coding.assignment.enums.Operation;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Component that handles multiple chained calculations using the Calculator.
 * It processes a list of operations sequentially, starting from an initial value.
 */

@Component
public class MultipleCalculator {

    private final Calculator calculator;

    public MultipleCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Processes a chain of operations starting from the initial value.
     *
     * @param initialValue The initial value to start the calculation.
     * @param chainOperations The list of operations to perform sequentially.
     * @return The final result after processing all operations.
     */
    public Number processChainOperations(Number initialValue, List<OperationRequest> chainOperations) {
        // Start with the initial value
        Number currentResult = initialValue;

        // Process each operation sequentially
        for (OperationRequest operationRequest : chainOperations) {
            Operation operation = Operation.valueOf(operationRequest.getOp().toUpperCase());
            currentResult = calculator.calculate(operation, currentResult, operationRequest.getNum());
        }

        // Return the final result after all operations are processed
        return currentResult;
    }
}

