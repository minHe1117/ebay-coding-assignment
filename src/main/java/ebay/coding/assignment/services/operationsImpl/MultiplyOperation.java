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
 * MultiplyOperation performs multiplication between two Number values.
 * This class implements the Operations interface and uses BigDecimal to handle
 * different types of numeric values.
 */
@Service
 public class MultiplyOperation implements Operations {

    private static final Logger log = LoggerFactory.getLogger(MultiplyOperation.class);

    static {
        // Register MultiplyOperation in the factory, storing its class metadata
        OperationFactory.registerOperation(Operation.MULTIPLY, MultiplyOperation.class);
    }

    /**
      * @param num1 First number
      * @param num2 Second number
      * @return the product of num1 and num2 as a BigDecimal
      * @throws IllegalArgumentException if either num1 or num2 is null
      */
    @Override
    public Number apply(Number num1, Number num2) {
        try {
            // Convert both numbers to BigDecimal for safe calculation
            BigDecimal bigDecimalNum1 = NumberUtils.toBigDecimal(num1);
            BigDecimal bigDecimalNum2 = NumberUtils.toBigDecimal(num2);

            // Perform multiplication
            BigDecimal result = bigDecimalNum1.multiply(bigDecimalNum2);
            log.debug("Multiplication: {} * {} = {}", bigDecimalNum1, bigDecimalNum2, result);
            return result;
        } catch (IllegalArgumentException ex) {
            log.error("Error in MultiplyOperation: {}", ex.getMessage());
            throw ex;
        }
    }
}
