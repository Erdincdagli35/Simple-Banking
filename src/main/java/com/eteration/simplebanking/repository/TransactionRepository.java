package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing transaction data in the database.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    //List<Transaction> findAllByAccount_Id(Long id);
}

