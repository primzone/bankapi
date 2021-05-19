package com.bank.api.service;

import com.bank.api.entity.Account;
import com.bank.api.entity.Card;
import com.bank.api.entity.User;
import com.bank.api.exception_handling.account_exceptions.InsufficientFundsOnAccountexception;
import com.bank.api.exception_handling.account_exceptions.NoSuchAccountException;
import com.bank.api.exception_handling.user_exceptions.NoSuchUserException;
import com.bank.api.repository.AccountRepository;
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

        return userService.findById(id).getAccounts();
//        Optional<User> userById = userService.findById(id);
//        if (userById.isPresent()) return userById.get().getAccounts();
//        else throw new NoSuchUserException("User by id = " + id + " not found");

    }

    public Account findByAccountNumber(String accountNumber) {

        Account byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if (byAccountNumber == null) {
            throw new NoSuchAccountException("Account with account number = " + accountNumber + " not found");
        }
        return byAccountNumber;
    }

    public void save(Account accountNumber) {
        accountRepository.save(accountNumber);
    }


    public Account findByCardsIsContaining(Card card) {

        //Находим счет к которому привязана карта
        Account byCardsIsContaining = accountRepository.findByCardsIsContaining(card);
        if (byCardsIsContaining == null) throw new NoSuchAccountException("Account by card number = " + card.getCardNumber() + " not found");
        return byCardsIsContaining;

    }


    public Account findById(long id) {
        if (!accountRepository.findById(id).isPresent()){
            throw new NoSuchAccountException("Account by id = " + id + " not found");
        }
        return accountRepository.findById(id).get();
    }

    public void checkBalanceforTransaction(Account account, double amount){
        if (account.getBalance() < amount){
            throw new InsufficientFundsOnAccountexception("Недостаточно средств для перевода," +
                    " На счету: "+ account.getBalance() + ", Перевод: " + amount);
        }

    }
}
