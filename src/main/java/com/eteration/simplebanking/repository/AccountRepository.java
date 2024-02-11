package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing account data in the database.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * Finds an account by its account number.
     * @param accountNumber the account number to search for
     * @return the found account, or null if no account is found with the given account number
     */
    Account findOneByAccountNumber(String accountNumber);
}
