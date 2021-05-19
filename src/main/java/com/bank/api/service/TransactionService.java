package com.bank.api.service;

import com.bank.api.entity.Transaction;
import com.bank.api.exception_handling.transaction_exceptions.NoSuchTransactionException;
import com.bank.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public void save(Transaction transaction) {
       transactionRepository.save(transaction);
    }

    public Transaction findByTransactionNumber(long transactionNumber) {

        if (!transactionRepository.findByTransactionNumber(transactionNumber).isPresent()){
            throw new NoSuchTransactionException("Transaction by transaction number = " + transactionNumber + " not found");
        }
        return transactionRepository.findByTransactionNumber(transactionNumber).get();
    }

    public long findMaxOfTransactionNumber() {
        try {
            return transactionRepository.findMaxOfTransactionNumber();
        }
        catch (Exception e){
            return 0;
        }
    }

}
