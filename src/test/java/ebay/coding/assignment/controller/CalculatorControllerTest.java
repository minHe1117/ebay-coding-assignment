package ebay.coding.assignment.controller;

import ebay.coding.assignment.dto.MultipleOperationsRequest;
import ebay.coding.assignment.dto.OperationRequest;
import ebay.coding.assignment.dto.SingleOperationRequest;
import ebay.coding.assignment.dto.common.ServiceRequest;
import ebay.coding.assignment.dto.common.ServiceResponse;
import ebay.coding.assignment.enums.Operation;
import ebay.coding.assignment.exceptions.ValidationException;
import ebay.coding.assignment.services.Calculator;
import ebay.coding.assignment.services.MultipleCalculator;
import ebay.coding.assignment.validators.CalculatorValidator;
import ebay.coding.assignment.validators.MultipleOperationsValidator;
import ebay.coding.assignment.validators.SingleOperationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorControllerTest {

    @Mock
    private CalculatorValidator calculatorValidator;

    @Mock
    private SingleOperationValidator singleOperationValidator;

    @Mock
    private MultipleOperationsValidator multipleOperationsValidator;

    @Mock
    private MultipleCalculator multipleCalculator;

    @Mock
    private Calculator calculator;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for successfully processing a single operation.
     * It verifies that the validator and calculator are called correctly and returns a valid response.
     */
    @Test
    void testProcessSingleOperation_Success() {
        ServiceRequest<SingleOperationRequest> serviceRequest = new ServiceRequest<>();
        SingleOperationRequest singleRequest = new SingleOperationRequest();
        singleRequest.setNum1(10);
        singleRequest.setNum2(5);
        serviceRequest.setPayload(singleRequest);

        when(singleOperationValidator.validate(singleRequest)).thenReturn(Operation.ADD);
        when(calculator.calculate(Operation.ADD, 10, 5)).thenReturn(15);

        ServiceResponse<Number> response = calculatorController.processSingleOperation(serviceRequest);

        assertEquals(15, response.getData());
        assertTrue(response.isSuccess());

        verify(singleOperationValidator, times(1)).validate(singleRequest);
        verify(calculator, times(1)).calculate(Operation.ADD, 10, 5);
    }
    /**
     * Test case for validation exception during single operation processing.
     * It ensures that the proper error response is returned when validation fails.
     */
    @Test
    void testProcessSingleOperation_ValidationException() {
        ServiceRequest<SingleOperationRequest> serviceRequest = new ServiceRequest<>();

        doThrow(new ValidationException("Invalid request")).when(calculatorValidator).validate(serviceRequest);

        ServiceResponse<Number> response = calculatorController.processSingleOperation(serviceRequest);


        assertEquals("400", response.getStatusCode());
        assertFalse(response.isSuccess());

        verify(calculator, never()).calculate(any(), any(), any());
    }
    /**
     * Test case for internal server error during single operation processing.
     * It verifies that an error response is returned when an unexpected exception occurs.
     */
    @Test
    void testProcessSingleOperation_InternalServerError() {
        ServiceRequest<SingleOperationRequest> serviceRequest = new ServiceRequest<>();
        SingleOperationRequest singleRequest = new SingleOperationRequest();
        singleRequest.setNum1(10);
        singleRequest.setNum2(5);
        serviceRequest.setPayload(singleRequest);

        when(singleOperationValidator.validate(any())).thenReturn(Operation.ADD);
        when(calculator.calculate(Operation.ADD, 10, 5)).thenThrow(new RuntimeException("Internal error"));

        ServiceResponse<Number> response = calculatorController.processSingleOperation(serviceRequest);

        assertEquals("500", response.getStatusCode());  // Check if the response code is 500 (internal server error)
        assertFalse(response.isSuccess());  // Ensure the response is unsuccessful

        verify(calculator, times(1)).calculate(any(), any(), any());
    }


    /**
     * Test case for successfully processing multiple operations (chain).
     * It verifies that multiple operations are processed and the correct final result is returned.
     */
    @Test
    void testProcessChainOperation_Success() {
        ServiceRequest<MultipleOperationsRequest> serviceRequest = new ServiceRequest<>();
        MultipleOperationsRequest multipleRequest = new MultipleOperationsRequest();
        serviceRequest.setPayload(multipleRequest);

        when(multipleCalculator.processChainOperations(any(), any())).thenReturn(25);

        ServiceResponse<Number> response = calculatorController.processChainOperation(serviceRequest);

        assertEquals(25, response.getData());
        assertTrue(response.isSuccess());

        verify(multipleCalculator, times(1)).processChainOperations(any(), any());
    }

    /**
     * Test case for validation exception during chain operation processing.
     * It ensures that the correct error response is returned when validation fails.
     */
    @Test
    void testProcessChainOperation_ValidationException() {
        ServiceRequest<MultipleOperationsRequest> serviceRequest = new ServiceRequest<>();

        doThrow(new ValidationException("Invalid chain request")).when(calculatorValidator).validate(any());

        ServiceResponse<Number> response = calculatorController.processChainOperation(serviceRequest);

        assertEquals("400", response.getStatusCode());
        assertFalse(response.isSuccess());

        verify(multipleCalculator, never()).processChainOperations(any(), any());
    }
    @Test
    void testProcessChainOperation_InternalServerError() {
        ServiceRequest<MultipleOperationsRequest> serviceRequest = new ServiceRequest<>();
        MultipleOperationsRequest multipleRequest = new MultipleOperationsRequest();
        multipleRequest.setInitialValue(10);
        multipleRequest.setChainOperations(Arrays.asList(
                new OperationRequest("ADD", 5),
                new OperationRequest("MULTIPLY", 2)
        ));
        serviceRequest.setPayload(multipleRequest);

        doNothing().when(multipleOperationsValidator).validate(any(MultipleOperationsRequest.class));
        when(multipleCalculator.processChainOperations(any(), any())).thenThrow(new RuntimeException("Internal error"));

        ServiceResponse<Number> response = calculatorController.processChainOperation(serviceRequest);

        assertEquals("500", response.getStatusCode());  // Check if the response code is 500 (internal server error)
        assertFalse(response.isSuccess());  // Ensure the response is unsuccessful

        verify(multipleCalculator, times(1)).processChainOperations(any(), any());
    }
}
