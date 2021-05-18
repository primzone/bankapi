package com.bank.api.repository;

import com.bank.api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findByTransactionNumber(long s);

    @Query("SELECT MAX(t.transactionNumber) FROM Transaction t")
    long findMaxOfTransactionNumber();

}
