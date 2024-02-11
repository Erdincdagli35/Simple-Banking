package com.eteration.simplebanking.services;

import com.eteration.simplebanking.exception.FailedDepositReturn;
import com.eteration.simplebanking.exception.FailedWithDrawalReturn;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.*;

/**
 * Service interface for managing account-related operations.
 */
public interface AccountService {

    /**
     * Credits an account with the specified deposit transaction.
     * @param accountNumber the account number to credit
     * @param depositTransaction the deposit transaction
     * @return the status of the transaction
     * @throws InsufficientBalanceException if the account balance is insufficient
     * @throws FailedDepositReturn if the deposit process fails
     */
    TransactionStatus credit(String accountNumber, DepositTransaction depositTransaction) throws InsufficientBalanceException, FailedDepositReturn;

    /**
     * Debits an account with the specified withdrawal transaction.
     * @param accountNumber the account number to debit
     * @param withdrawalTransaction the withdrawal transaction
     * @return the status of the transaction
     * @throws InsufficientBalanceException if the account balance is insufficient
     * @throws FailedWithDrawalReturn if the withdrawal process fails
     */
    TransactionStatus debit(String accountNumber, WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException, FailedWithDrawalReturn;

    /**
     * Retrieves the account with the specified account number.
     * @param accountNumber the account number to retrieve
     * @return the account
     */
    Account getAccount(String accountNumber);

    /**
     * Finds the account with the specified account number.
     * @param accountNumber the account number to find
     * @return the found account
     */
    Account findAccount(String accountNumber);
}
