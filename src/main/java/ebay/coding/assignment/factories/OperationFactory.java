package ebay.coding.assignment.factories;

import ebay.coding.assignment.enums.Operation;
import ebay.coding.assignment.exceptions.UnsupportedNumberTypeException;
import ebay.coding.assignment.services.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class responsible for creating instances of operations based on the Operation enum.
 */
public class OperationFactory {

    private static final Logger log = LoggerFactory.getLogger(OperationFactory.class);


    // A map to store the association between Operation enum and its corresponding operation class
    private static final Map<Operation, Class<? extends Operations>> operationMap = new HashMap<>();

    // Register a new operation
    public static void registerOperation(Operation operation, Class<? extends Operations> operationClass) {
        operationMap.put(operation, operationClass);
    }

    // Retrieve the appropriate operation instance based on the Operation enum
    public static Operations getOperation(Operation operation) {
        Class<? extends Operations> operationClass = operationMap.get(operation);

        if (operationClass == null) {
            log.error("Operation not supported: {}", operation);
            throw new UnsupportedNumberTypeException("Operation not supported: " + operation);
        }

        try {
            // Use reflection to create the operation instance
            return operationClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("Failed to create operation instance for class: {}", operationClass.getName(), e);
            throw new RuntimeException("Failed to create operation instance: " + e.getMessage());
        }
    }

}
