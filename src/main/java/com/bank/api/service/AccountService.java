package com.bank.api.service;

import com.bank.api.entity.Account;
import com.bank.api.entity.User;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserService userService;

    public List<Account> getAllAccounts(long id) {

        Optional<User> userById = userService.findById(id);
        if (userById.isPresent()) return userById.get().getAccounts();
        else return new ArrayList<Account>();


    }


    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public void save(Account accountNumber) {

        accountRepository.save(accountNumber);
    }
}
