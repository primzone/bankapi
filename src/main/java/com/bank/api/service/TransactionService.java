package com.bank.api.service;

import com.bank.api.entity.Transaction;
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

    public Transaction findBytransactionNumber(long transactionNumber) {

        return transactionRepository.findByTransactionNumber(transactionNumber);

    }

    public long findMaxOfTransactionNumber() {
        try {
            return transactionRepository.findMaxOfTransactionNumber();
        }
        catch (Exception e){
            return 1;
        }

    }
}
