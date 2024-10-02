package ebay.coding.assignment.services;

import ebay.coding.assignment.enums.Operation;
import ebay.coding.assignment.factories.OperationFactory;
import org.springframework.stereotype.Component;

/**
 * Calculator class responsible for performing calculations
 * using various operations like addition, subtraction, multiplication, and division.
 */
@Component
public class Calculator {

    /**
     * Calculate the result based on the operation.
     * @param operation The type of operation (ADD, SUBTRACT, MULTIPLY, DIVIDE).
     * @param num1 The first number.
     * @param num2 The second number.
     * @return The result of the calculation as a Number.
     */
    public Number calculate(Operation operation, Number num1, Number num2) {
        // Use factory to get the appropriate operation instance
        Operations operationStrategy = OperationFactory.getOperation(operation);
        return operationStrategy.apply(num1, num2);
    }
}