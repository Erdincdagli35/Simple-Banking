package com.eteration.simplebanking.model;

import lombok.Data;
import javax.persistence.Entity;
/**
 * Represents a withdrawal transaction.
 * Inherits from the Transaction class.
 */
@Data
@Entity
public class WithdrawalTransaction extends Transaction {

    private double amount;
    private TransactionType type;

    /**
     * Constructs a new WithdrawalTransaction with default values.
     */
    public WithdrawalTransaction() {
        this.type = TransactionType.WithdrawalTransaction;
    }

    /**
     * Constructs a new WithdrawalTransaction with the specified amount.
     *
     * @param amount the amount of money involved in the withdrawal transaction
     */
    public WithdrawalTransaction(double amount) {
        super(amount);
        this.amount = amount;
        this.type = TransactionType.WithdrawalTransaction;
    }

    // Getters and setters omitted for brevity

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the type of the transaction.
     *
     * @return the type of the transaction
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Sets the type of the transaction.
     *
     * @param type the type of the transaction
     */
    public void setType(TransactionType type) {
        this.type = type;
    }
}



