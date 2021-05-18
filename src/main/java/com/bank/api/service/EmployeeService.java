package com.bank.api.service;

import com.bank.api.entity.Account;
import com.bank.api.entity.Card;
import com.bank.api.entity.Transaction;
import com.bank.api.entity.User;
import com.bank.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    CardService cardService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    ContractorService contractorService;


    public void saveUser(User user) {
        userService.saveUser(user);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void addAccountToUser(long userId) {

        Account account = new Account(Utils.generateAccountNumber(), userService.findById(userId).get());
        accountService.save(account);

    }

    public void confirmCard(String cardNumber) {

        Card byCardNumber = cardService.findByCardNumber(cardNumber);
        byCardNumber.setConfirmation(true);
        cardService.save(byCardNumber);

    }

    public void confirmTransaction(long transactionNumber) {

        //Находим транзакцию по номеру
        Transaction bytransactionNumber = transactionService.findBytransactionNumber(transactionNumber);
        //Устанавливаем Потверждение = true и сохраняем
        bytransactionNumber.setConfirmation(true);
        transactionService.save(bytransactionNumber);
        //Находим счета по отправителя и получателся по транзакции
        Account senderAccount = accountService.findById(bytransactionNumber.getSenderAccountNumberId());
        Account recipientAccount = accountService.findById(bytransactionNumber.getRecipientAccountNumberId());
        //Исправляем баланс счетов
        senderAccount.setBalance(senderAccount.getBalance() - bytransactionNumber.getAmount());
        recipientAccount.setBalance(recipientAccount.getBalance() + bytransactionNumber.getAmount());
        //Сохраняем счета
        accountService.save(senderAccount);
        accountService.save(recipientAccount);

    }


    public void addAccountToContractor(long contractorId) {

        Account account = new Account(Utils.generateAccountNumber(), contractorService.findById(contractorId).get());
        accountService.save(account);
    }
}
