package ebay.coding.assignment.services.operationsImpl;

import ebay.coding.assignment.enums.Operation;
import ebay.coding.assignment.factories.OperationFactory;
import org.springframework.stereotype.Service;
import ebay.coding.assignment.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ebay.coding.assignment.services.Operations;

import java.math.BigDecimal;

/**
 * DivideOperation performs division between two Number values.
 * This class implements the Operations interface and uses BigDecimal to handle
 * different types of numeric values.
 */
@Service
public class DivideOperation implements Operations {

    private static final Logger log = LoggerFactory.getLogger(DivideOperation.class);

    static {
        // Register DivideOperation in the factory, storing its class metadata
        OperationFactory.registerOperation(Operation.DIVIDE, DivideOperation.class);
    }

    /**
     * @param num1 Dividend
     * @param num2 Divisor
     * @return the result of num1 divided by num2 as a BigDecimal
     * @throws ArithmeticException if division by zero occurs
     * @throws IllegalArgumentException if either num1 or num2 is null
     */
    @Override
    public Number apply(Number num1, Number num2) {
        try {
            // Convert both numbers to BigDecimal for safe calculation
            BigDecimal bigDecimalNum1 = NumberUtils.toBigDecimal(num1);
            BigDecimal bigDecimalNum2 = NumberUtils.toBigDecimal(num2);

            // Check for division by zero
            if (bigDecimalNum2.compareTo(BigDecimal.ZERO) == 0) {
                log.error("Division by zero error: num1 = {}, num2 = {}", num1, num2);
                throw new ArithmeticException("Division by zero is not allowed");
            }
            // Perform division
            BigDecimal result = bigDecimalNum1.divide(bigDecimalNum2);
            log.debug("Division: {} / {} = {}", bigDecimalNum1, bigDecimalNum2, result);
            return result;
        } catch (IllegalArgumentException | ArithmeticException ex) {
            log.error("Error in DivideOperation: {}", ex.getMessage());
            throw ex;
        }
    }
}
