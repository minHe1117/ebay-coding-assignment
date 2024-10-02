package ebay.coding.assignment.services.operationsImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

class OperationTests {

    private AddOperation addOperation;
    private SubtractOperation subtractOperation;
    private MultiplyOperation multiplyOperation;
    private DivideOperation divideOperation;

    @BeforeEach
    void setUp() {
        addOperation = new AddOperation();
        subtractOperation = new SubtractOperation();
        multiplyOperation = new MultiplyOperation();
        divideOperation = new DivideOperation();
    }

    /**
     * Test the add operation with valid inputs.
     */
    @Test
    void testAddOperation_Success() {
        Number result = addOperation.apply(10, 5);
        assertEquals(BigDecimal.valueOf(15), result);
    }

    /**
     * Test the subtract operation with valid inputs.
     */
    @Test
    void testSubtractOperation_Success() {
        Number result = subtractOperation.apply(10, 5);
        assertEquals(BigDecimal.valueOf(5), result);
    }

    /**
     * Test the multiply operation with valid inputs.
     */
    @Test
    void testMultiplyOperation_Success() {
        Number result = multiplyOperation.apply(10, 5);
        assertEquals(BigDecimal.valueOf(50), result);
    }

    /**
     * Test the divide operation with valid inputs.
     */
    @Test
    void testDivideOperation_Success() {
        Number result = divideOperation.apply(10, 5);
        assertEquals(BigDecimal.valueOf(2), result);
    }

    /**
     * Test division by zero, which should throw an ArithmeticException.
     */
    @Test
    void testDivideOperation_DivisionByZero() {
        assertThrows(ArithmeticException.class, () -> divideOperation.apply(10, 0));
    }

    /**
     * Test add operation with null inputs, which should throw IllegalArgumentException.
     */
    @Test
    void testAddOperation_NullInputs() {
        assertThrows(IllegalArgumentException.class, () -> addOperation.apply(null, 5));
    }

    /**
     * Test subtract operation with null inputs, which should throw IllegalArgumentException.
     */
    @Test
    void testSubtractOperation_NullInputs() {
        assertThrows(IllegalArgumentException.class, () -> subtractOperation.apply(5, null));
    }

    /**
     * Test multiply operation with null inputs, which should throw IllegalArgumentException.
     */
    @Test
    void testMultiplyOperation_NullInputs() {
        assertThrows(IllegalArgumentException.class, () -> multiplyOperation.apply(null, 5));
    }

    /**
     * Test divide operation with null inputs, which should throw IllegalArgumentException.
     */
    @Test
    void testDivideOperation_NullInputs() {
        assertThrows(IllegalArgumentException.class, () -> divideOperation.apply(5, null));
    }
}
