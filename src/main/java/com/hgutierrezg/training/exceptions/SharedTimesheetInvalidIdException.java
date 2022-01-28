package com.hgutierrezg.training.exceptions;

public class SharedTimesheetInvalidIdException extends RuntimeException {

    public SharedTimesheetInvalidIdException() {
        super();
    }

    public SharedTimesheetInvalidIdException(String message) {
        super(message);
    }

    public SharedTimesheetInvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
