package com.eteration.simplebanking.exception;

/**
 * Exception thrown when a withdrawal transaction fails to be processed.
 */
public class FailedWithDrawalReturn extends Exception {
    /**
     * Constructs a new FailedWithDrawalReturn with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public FailedWithDrawalReturn(String message) {
        super(message);
    }
}

