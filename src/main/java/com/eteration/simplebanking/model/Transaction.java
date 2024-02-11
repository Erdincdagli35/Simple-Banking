package com.eteration.simplebanking.model;

import lombok.ToString;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Represents a generic banking transaction.
 * This is an abstract class and should be extended by specific transaction types.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@ToString
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ZonedDateTime date;

    private double amount;

    private String approvalCode;

    @Enumerated(EnumType.STRING)
    private TransactionType type = TransactionType.NONE;
/*
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;*/

    /**
     * Constructs a new Transaction with default values.
     */
    public Transaction() {
        this.date = generateFormattedDateTime();
    }

    /**
     * Constructs a new Transaction with the specified amount.
     *
     * @param amount the amount of money involved in the transaction
     */
    public Transaction(double amount) {
        this.amount = amount;
        this.date = generateFormattedDateTime();
    }

    // Getters and setters omitted for brevity

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    /**
     * Generates a formatted date and time for the transaction.
     *
     * @return the formatted date and time
     */
    public ZonedDateTime generateFormattedDateTime() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
