package com.eteration.simplebanking.exception;

/**
 * Exception thrown when a deposit transaction fails to be processed.
 */
public class FailedDepositReturn extends Exception {
    /**
     * Constructs a new FailedDepositReturn with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public FailedDepositReturn(String message) {
        super(message);
    }
}

