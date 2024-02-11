package com.eteration.simplebanking.model;

import lombok.Data;

import javax.persistence.Entity;
/**
 * Represents a bill payment transaction.
 * Inherits from the Transaction class.
 */
@Data
@Entity
public class BillPaymentTransaction extends Transaction {
    private String paymentTitle;
    private String paymentNo;
    private double payee;
    private TransactionType type;

    /**
     * Constructs a new BillPaymentTransaction with default values.
     */
    public BillPaymentTransaction() {
        this.type = TransactionType.WithdrawalTransaction;
    }

    /**
     * Constructs a new BillPaymentTransaction with the specified payee amount.
     * @param payee the amount of money involved in the transaction
     */
    public BillPaymentTransaction(double payee) {
        super(payee);
        this.payee = payee;
        this.type = TransactionType.WithdrawalTransaction;
    }

    /**
     * Constructs a new BillPaymentTransaction with the specified payment title, payment number, and payee amount.
     * @param paymentTitle the title of the payment
     * @param paymentNo the number associated with the payment
     * @param payee the amount of money involved in the transaction
     */
    public BillPaymentTransaction(String paymentTitle, String paymentNo, double payee) {
        super(payee);
        this.paymentTitle = paymentTitle;
        this.paymentNo = paymentNo;
        this.payee = payee;
        this.type = TransactionType.WithdrawalTransaction;
    }

    // Getters and setters omitted for brevity

    /**
     * Gets the type of the transaction.
     * @return the type of the transaction
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Sets the type of the transaction.
     * @param type the type of the transaction
     */
    public void setType(TransactionType type) {
        this.type = type;
    }
}
