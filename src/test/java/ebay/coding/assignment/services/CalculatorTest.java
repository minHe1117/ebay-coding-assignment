package ebay.coding.assignment.services;

import ebay.coding.assignment.enums.Operation;
import ebay.coding.assignment.exceptions.UnsupportedNumberTypeException;
import ebay.coding.assignment.factories.OperationFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    /**
     * Test ADD operation.
     */
    @Test
    void testCalculate_AddOperation() {
        Operations addOperation = mock(Operations.class);


        try (MockedStatic<OperationFactory> operationFactoryMock = Mockito.mockStatic(OperationFactory.class)) {
            operationFactoryMock.when(() -> OperationFactory.getOperation(Operation.ADD))
                    .thenReturn(addOperation);

            when(addOperation.apply(10, 5)).thenReturn(BigDecimal.valueOf(15));

            Number result = calculator.calculate(Operation.ADD, 10, 5);

            assertEquals(BigDecimal.valueOf(15), result);

            operationFactoryMock.verify(() -> OperationFactory.getOperation(Operation.ADD), times(1));

            verify(addOperation, times(1)).apply(10, 5);
        }
    }

    /**
     * Test SUBTRACT operation.
     */
    @Test
    void testCalculate_SubtractOperation() {
        Operations subtractOperation = mock(Operations.class);

        try (MockedStatic<OperationFactory> operationFactoryMock = Mockito.mockStatic(OperationFactory.class)) {
            operationFactoryMock.when(() -> OperationFactory.getOperation(Operation.SUBTRACT))
                    .thenReturn(subtractOperation);

            when(subtractOperation.apply(10, 5)).thenReturn(BigDecimal.valueOf(5));

            Number result = calculator.calculate(Operation.SUBTRACT, 10, 5);

            assertEquals(BigDecimal.valueOf(5), result);

            operationFactoryMock.verify(() -> OperationFactory.getOperation(Operation.SUBTRACT), times(1));

            verify(subtractOperation, times(1)).apply(10, 5);
        }
    }

    /**
     * Test MULTIPLY operation.
     */
    @Test
    void testCalculate_MultiplyOperation() {
        Operations multiplyOperation = mock(Operations.class);

        try (MockedStatic<OperationFactory> operationFactoryMock = Mockito.mockStatic(OperationFactory.class)) {
            operationFactoryMock.when(() -> OperationFactory.getOperation(Operation.MULTIPLY))
                    .thenReturn(multiplyOperation);

            when(multiplyOperation.apply(10, 5)).thenReturn(BigDecimal.valueOf(50));

            Number result = calculator.calculate(Operation.MULTIPLY, 10, 5);

            assertEquals(BigDecimal.valueOf(50), result);

            operationFactoryMock.verify(() -> OperationFactory.getOperation(Operation.MULTIPLY), times(1));

            verify(multiplyOperation, times(1)).apply(10, 5);
        }
    }

    /**
     * Test DIVIDE operation.
     */
    @Test
    void testCalculate_DivideOperation() {
        Operations divideOperation = mock(Operations.class);

        try (MockedStatic<OperationFactory> operationFactoryMock = Mockito.mockStatic(OperationFactory.class)) {
            operationFactoryMock.when(() -> OperationFactory.getOperation(Operation.DIVIDE))
                    .thenReturn(divideOperation);

            when(divideOperation.apply(10, 5)).thenReturn(BigDecimal.valueOf(2));

            Number result = calculator.calculate(Operation.DIVIDE, 10, 5);

            assertEquals(BigDecimal.valueOf(2), result);

            operationFactoryMock.verify(() -> OperationFactory.getOperation(Operation.DIVIDE), times(1));

            verify(divideOperation, times(1)).apply(10, 5);
        }
    }

    /**
     * Test invalid operation type. It should throw IllegalArgumentException.
     */
    @Test
    void testCalculate_InvalidOperation() {
        // Try an invalid operation, expecting an UnsupportedNumberTypeException
        assertThrows(UnsupportedNumberTypeException.class, () -> calculator.calculate(null, 10, 5));
    }
}
