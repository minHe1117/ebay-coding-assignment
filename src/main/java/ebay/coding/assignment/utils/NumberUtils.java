package ebay.coding.assignment.utils;

import ebay.coding.assignment.exceptions.UnsupportedNumberTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class NumberUtils {

    private static final Logger log = LoggerFactory.getLogger(NumberUtils.class);

    public static  BigDecimal toBigDecimal(Number number) {
        return getBigDecimal(number);
    }

    public static BigDecimal getBigDecimal(Number number) throws UnsupportedNumberTypeException {
        if (number == null) {
            log.error("The input number is null");
            throw new IllegalArgumentException("The input number cannot be null");
        }

        if (number instanceof BigDecimal) {
            return (BigDecimal) number;  // If it is already a BigDecimal, return it directly
        } else if (number instanceof Integer || number instanceof Long) {
            return BigDecimal.valueOf(number.longValue());  // Handle integer types
        } else if (number instanceof Double || number instanceof Float) {
            return BigDecimal.valueOf(number.doubleValue());  // Handle floating-point types
        } else {
            log.error("Unsupported number type: {}", number.getClass().getName());
            throw new UnsupportedNumberTypeException("Unsupported number type: " + number.getClass().getName());
        }
    }
}
