package ebay.coding.assignment.exceptions;

public class InvalidPayloadException extends RuntimeException {
    public InvalidPayloadException(String message) {
        super("Invalid payload: " + message);
    }
}