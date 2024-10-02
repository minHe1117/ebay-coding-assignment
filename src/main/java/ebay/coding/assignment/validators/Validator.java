package ebay.coding.assignment.validators;

import ebay.coding.assignment.exceptions.ValidationException;

public interface Validator<T> {
    void validate(T request) throws ValidationException;
}
