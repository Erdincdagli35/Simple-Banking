package com.eteration.simplebanking.services.implementation;

import com.eteration.simplebanking.exception.FailedDepositReturn;
import com.eteration.simplebanking.exception.FailedWithDrawalReturn;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import com.eteration.simplebanking.services.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the AccountService interface providing business logic for account-related operations.
 */
@Log4j2
@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Constructs a new AccountServiceImpl with the specified AccountRepository.
     *
     * @param accountRepository the repository for managing account data
     */
    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Implementations of AccountService methods...

    /**
     * Credits an account with the specified deposit transaction.
     *
     * @param accountNumber      the account number to credit
     * @param depositTransaction the deposit transaction
     * @return the status of the transaction
     * @throws InsufficientBalanceException if the account balance is insufficient
     * @throws FailedDepositReturn          if the deposit process fails
     */
    @Override
    public TransactionStatus credit(String accountNumber, DepositTransaction depositTransaction) throws InsufficientBalanceException, FailedDepositReturn {
        Account account = findAccount(accountNumber);
        depositTransaction.setApprovalCode(successTransactionStatus().getApproveCode());

        List<Transaction> transactionList = account.getTransactions() == null ? new ArrayList<>() : account.getTransactions();

        transactionList.add(depositTransaction);
        account.setTransactions(transactionList);
        transactionRepository.save((Transaction) depositTransaction);

        try {
            account.deposit(depositTransaction.getAmount());
            accountRepository.save(account);
            return successTransactionStatus();
        } catch (Exception e) {
            throw new FailedDepositReturn("Credit process has failed");
        }
    }

    /**
     * Debits an account with the specified withdrawal transaction.
     *
     * @param accountNumber         the account number to debit
     * @param withdrawalTransaction the withdrawal transaction
     * @return the status of the transaction
     * @throws InsufficientBalanceException if the account balance is insufficient
     * @throws FailedWithDrawalReturn       if the withdrawal process fails
     */
    @Override
    public TransactionStatus debit(String accountNumber, WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException, FailedWithDrawalReturn {
        Account account = findAccount(accountNumber);
        withdrawalTransaction.setApprovalCode(successTransactionStatus().getApproveCode());

        List<Transaction> transactionList = account.getTransactions();
        transactionList.add(withdrawalTransaction);
        account.setTransactions(transactionList);
        transactionRepository.save((Transaction) withdrawalTransaction);

        try {
            account.withdraw(withdrawalTransaction.getAmount());
            accountRepository.save(account);
            return successTransactionStatus();
        } catch (Exception e) {
            throw new FailedWithDrawalReturn("Debit process has failed");
        }
    }

    /**
     * Retrieves the account with the specified account number.
     *
     * @param accountNumber the account number to retrieve
     * @return the account
     */
    @Override
    public Account getAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        return account;
    }

    /**
     * Finds the account with the specified account number.
     *
     * @param accountNumber the account number to find
     * @return the found account
     */
    @Override
    public Account findAccount(String accountNumber) {
        Account account = accountRepository.findOneByAccountNumber(accountNumber);
        return account;
    }

    /**
     * Generates a success transaction status.
     *
     * @return the success transaction status
     */
    public TransactionStatus successTransactionStatus() {
        String approvalCode = generateApprovalCode();
        TransactionStatus TransactionStatus = new TransactionStatus(approvalCode, "OK");
        return TransactionStatus;
    }

    /**
     * Generates an approval code for transactions.
     *
     * @return the generated approval code
     */
    public String generateApprovalCode() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
