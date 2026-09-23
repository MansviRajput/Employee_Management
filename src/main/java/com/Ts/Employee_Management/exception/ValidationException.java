package com.Ts.Employee_Management.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<String> errors;

    public ValidationException(List<String> errors) {
        super(String.join("; ", errors));
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}