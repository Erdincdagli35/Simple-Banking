package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.exception.FailedDepositReturn;
import com.eteration.simplebanking.exception.FailedWithDrawalReturn;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.validation.AccountValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This controller handles HTTP requests related to account operations.
@RestController
@RequestMapping("/account/v1")
@EnableAutoConfiguration
@CrossOrigin
public class AccountController {

    private static final Logger logger = LogManager.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountValidation accountValidation;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Handles the POST request to credit an account.
    // It increases the balance of the specified account with the provided amount.
    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber,
                                                    @RequestBody DepositTransaction depositTransaction) throws InsufficientBalanceException, FailedDepositReturn {

        logger.info("Credit transaction requested for account number: {}", accountNumber);
        TransactionStatus result = accountService
                .credit(accountNumber, new DepositTransaction(depositTransaction.getAmount()));

        //You could use this validation when you are testing on the Postman
        /*
        if (!accountValidation.existsAccount(accountNumber)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result);
        }*/

        logger.info("Credit transaction result: {}", result);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);

    }

    // Handles the POST request to debit an account.
    // It decreases the balance of the specified account with the provided amount.
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber,
                                                   @RequestBody WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException, FailedWithDrawalReturn {

        logger.info("Debit transaction requested for account number: {}", accountNumber);
        TransactionStatus result =
                accountService.debit(accountNumber, new WithdrawalTransaction(withdrawalTransaction.getAmount()));

        //You could use this validation when you are testing on the Postman
        /*
        if (!accountValidation.existsAccount(accountNumber)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result);
        }
         */

        logger.info("Debit transaction result: {}", result);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    // Handles the GET request to retrieve information about an account.
    // It returns the details of the specified account.
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {

        logger.info("Get account request for account number: {}", accountNumber);

        Account result = accountService.getAccount(accountNumber);

        //You could use this validation when you are testing on the Postman
        /*
        if (!accountValidation.existsAccount(accountNumber)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result);
        }
      */

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
