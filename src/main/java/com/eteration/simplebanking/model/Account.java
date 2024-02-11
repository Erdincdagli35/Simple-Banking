package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String owner;
    private String accountNumber;
    private double balance = 0.0;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions = new ArrayList<>();

    public Account() {
    }

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Getters and setters omitted for brevity

    /**
     * Deposits money into the account.
     *
     * @param money the amount of money to deposit
     * @throws InsufficientBalanceException if the account balance is negative
     */
    public void deposit(double money) throws InsufficientBalanceException {
        if (money < 0) {
            System.out.println("Invalid amount for deposit: " + money);
        }

        if (balance < 0)
            throw new InsufficientBalanceException("You have a debt to the bank. Your debt: " + balance);
        else if (balance >= 0) {
            System.out.println("You do not owe anything to the bank now. Your balance: " + balance);
            balance = balance + money;
        }
        else {
            System.out.println("You do not owe anything to the bank. Thank you. Your balance: " + balance);
        }
    }

    /**
     * Withdraws money from the account.
     * @param money the amount of money to withdraw
     * @throws InsufficientBalanceException if the account balance is insufficient
     */
    public void withdraw(double money) throws InsufficientBalanceException {
        if (money <= 0) {
            System.out.println("Invalid amount for withdrawal: " + money);
            return;
        }

        if (balance >= money) {
            balance -= money;
            this.setBalance(balance);
        } else {
            throw new InsufficientBalanceException("The balance is not enough for this withdrawal process");
        }
    }

    /**
     * Posts a transaction to the account.
     * @param transaction the transaction to post
     * @throws InsufficientBalanceException if there's an issue with the account balance
     */
    public void post(Transaction transaction) throws InsufficientBalanceException {
        this.transactions.add(transaction);

        Transaction depositTransaction = new DepositTransaction();
        Transaction withdrawalTransaction = new WithdrawalTransaction();

        if (transaction.getType().equals(depositTransaction.getType())) {
            depositTransaction.setAmount(transaction.getAmount());
            deposit(transaction.getAmount());
        } else if (transaction.getType().equals(withdrawalTransaction.getType())) {
            withdrawalTransaction.setAmount(transaction.getAmount());
            withdraw(transaction.getAmount());
        } else {
            System.out.println("There is no option another");
        }

        System.out.println("Post process is done");
    }
}
