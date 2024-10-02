package ebay.coding.assignment.services;

import ebay.coding.assignment.dto.OperationRequest;
import ebay.coding.assignment.enums.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MultipleCalculatorTest {

    @Mock
    private Calculator calculator;

    @InjectMocks
    private MultipleCalculator multipleCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test successful chain of operations.
     * Example: initial value = 10, operations: [ADD 5, SUBTRACT 3]
     */
    @Test
    void testProcessChainOperations_Success() {
        Number initialValue = 10;
        List<OperationRequest> operationRequests = Arrays.asList(
                new OperationRequest("ADD", 5),
                new OperationRequest("SUBTRACT", 3)
        );

        when(calculator.calculate(Operation.ADD, initialValue, 5)).thenReturn(15);
        when(calculator.calculate(Operation.SUBTRACT, 15, 3)).thenReturn(12);

        Number result = multipleCalculator.processChainOperations(initialValue, operationRequests);

        assertEquals(12, result);

        verify(calculator, times(1)).calculate(Operation.ADD, 10, 5);
        verify(calculator, times(1)).calculate(Operation.SUBTRACT, 15, 3);
    }

    /**
     * Test empty chain operations, it should return the initial value.
     */
    @Test
    void testProcessChainOperations_EmptyOperations() {
        Number initialValue = 10;
        List<OperationRequest> emptyOperations = Collections.emptyList();

        Number result = multipleCalculator.processChainOperations(initialValue, emptyOperations);

        assertEquals(initialValue, result);

        verify(calculator, never()).calculate(any(), any(), any());
    }

    /**
     * Test invalid operation in the chain, it should throw an IllegalArgumentException.
     */
    @Test
    void testProcessChainOperations_InvalidOperation() {
        Number initialValue = 10;
        List<OperationRequest> operationRequests = Arrays.asList(
                new OperationRequest("INVALID_OP", 5)
        );

        assertThrows(IllegalArgumentException.class, () ->
                multipleCalculator.processChainOperations(initialValue, operationRequests));
    }

}
