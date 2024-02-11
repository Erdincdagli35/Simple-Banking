package com.eteration.simplebanking.exception;

/**
 * Exception thrown when there is an attempt to perform an operation that requires a balance exceeding the available funds.
 */
public class InsufficientBalanceException extends Exception {
    /**
     * Constructs a new InsufficientBalanceException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
