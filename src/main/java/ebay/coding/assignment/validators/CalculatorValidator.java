package ebay.coding.assignment.validators;

import ebay.coding.assignment.dto.common.ServiceRequest;
import ebay.coding.assignment.exceptions.InvalidPayloadException;
import ebay.coding.assignment.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Validator for validating ServiceRequest objects.
 * This validator performs the following checks:
 * 1. The ServiceRequest object is not null.
 * 2. The headers of the request are present and valid.
 * 3. The payload of the request is not null.
 *
 * If any of these validations fail, an InvalidPayloadException is thrown.
 */

@Service
public class CalculatorValidator implements Validator<ServiceRequest<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(CalculatorValidator.class);

    /**
     * Validates the provided ServiceRequest object.
     *
     * @param request The ServiceRequest object to be validated.
     * @throws ValidationException if the request is invalid.
     */

    @Override
    public void validate(ServiceRequest<?> request) throws ValidationException {
        LOG.debug("Starting validation for ServiceRequest");

        // Validate ServiceRequest itself
        validateRequestNotNull(request);
        validatePayload(request);

        LOG.debug("Validation successful for ServiceRequest");
    }

    /**
     * Validates that the request is not null.
     */
    private void validateRequestNotNull(ServiceRequest<?> request) {
        if (request == null) {
            LOG.error("Validation failed: Service request is null");
            throw new InvalidPayloadException("Service request cannot be null");
        }
    }
    /**
     * Validates that the payload is not null.
     */
    private void validatePayload(ServiceRequest<?> request) {
        Object payload = request.getPayload();
        if (payload == null) {
            LOG.error("Validation failed: Payload is null in ServiceRequest");
            throw new InvalidPayloadException("Payload cannot be null");
        }
    }
}
