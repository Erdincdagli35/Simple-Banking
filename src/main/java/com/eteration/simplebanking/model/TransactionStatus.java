package com.eteration.simplebanking.model;

import lombok.Data;

/**
 * Represents the status of a transaction.
 */
@Data
public class TransactionStatus {
    private String approveCode;
    private String status;

    /**
     * Constructs a new TransactionStatus with the specified approval code and status.
     *
     * @param approveCode the approval code of the transaction
     * @param status      the status of the transaction
     */
    public TransactionStatus(String approveCode, String status) {
        this.approveCode = approveCode;
        this.status = status;
    }

    // Getters and setters omitted for brevity
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApproveCode() {
        return approveCode;
    }
}
