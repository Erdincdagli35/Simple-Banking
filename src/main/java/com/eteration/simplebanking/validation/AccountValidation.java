package com.eteration.simplebanking.validation;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountValidation {

    @Autowired
    AccountRepository accountRepository;

    public boolean existsAccount(String accountNumber){
        Account account = accountRepository.findOneByAccountNumber(accountNumber);
        if (account != null) {
            return true;
        }else {
            return false;
        }
    }
}
