package com.bank.api.service;

import com.bank.api.entity.*;
import com.bank.api.exception_handling.account_exceptions.InsufficientFundsOnAccountexception;
import com.bank.api.exception_handling.account_exceptions.NoSuchAccountException;
import com.bank.api.exception_handling.card_exceptions.NoSuchCardException;
import com.bank.api.exception_handling.user_exceptions.NoSuchUserException;
import com.bank.api.repository.CardRepository;
import com.bank.api.utils.Utils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CardService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    ContractorService contractorService;
    @Autowired
    PaymentService paymentService;


    public List<Card> getAllCardsFromUserId(long id) {

        User user = userService.findById(id);
        List<Card> cardList = new ArrayList<>();

        //Добавляем карты каждого счета в лист
        for (Account account: user.getAccounts()){
            cardList.addAll(account.getCards());
        }

        return cardList;
    }

    public void addCardToUser(String accountNumber) {
        //находим счет, к но номеру
        Account byAccountNumber = accountService.findByAccountNumber(accountNumber);
        //добавляем к счету карту и сохраням
        byAccountNumber.addCardToAccount(new Card(Utils.generateCardNumber(), byAccountNumber));
      //  byAccountNumber.addCardToAccount(new Card("0000 0000 0000 0000", byAccountNumber));
        accountService.save(byAccountNumber);

    }

    public void refillAccountByCard(String cardNumber, Double amount) {

        //находим счет по номеру карты
       // Account byAccountNumber = accountService.findByCardsIsContaining(findByCardNumber(cardNumber));
        Account byAccountNumber = accountService.findById(findByCardNumber(cardNumber).getId());

        //добавляем к балансу
        byAccountNumber.setBalance(byAccountNumber.getBalance() + amount);
        //сохраняем
        accountService.save(byAccountNumber);
    }

    @Transactional
    public void transferToContractorAccount(String cardNumber, String contractorCardNumber, double amount) {


        //находим счета отправителя и получателя
        Account senderAccount = findByCardNumber(cardNumber).getAccount();
        if (senderAccount == null) throw new NoSuchAccountException("Счет отправителя по карте " + cardNumber + " не найден");
        accountService.checkBalanceforTransaction(senderAccount, amount);
        Account recipientAccount = findByCardNumber(contractorCardNumber).getAccount();
        if (recipientAccount == null) throw new NoSuchAccountException("Счет получателя по карте " + contractorCardNumber + " не найден");
        //получаем новый номер транзакции
        long newTransactionNumber = transactionService.findMaxOfTransactionNumber() + 1;

        //создаем и сохраняем транзакцию
        Transaction transaction = new Transaction(senderAccount.getId(), recipientAccount.getId(),
                                                cardNumber, contractorCardNumber,
                                                amount, newTransactionNumber);
        transactionService.save(transaction);

        //создаем и сохраняем для каждого счета пеймент для ссылки на транзакцию
        Payment payment = new Payment(senderAccount, transaction);
        Payment payment2 = new Payment(recipientAccount, transaction);

        paymentService.save(payment);
        paymentService.save(payment2);

    }


    public Card findByCardNumber(String cardNumber) {

        Card byCardNumber = cardRepository.findByCardNumber(cardNumber);
        if (byCardNumber == null) throw new NoSuchCardException("Карта по номеру " + cardNumber + " не найдена");
        return byCardNumber;

    }


    public void save(Card byCardNumber) {

            cardRepository.save(byCardNumber);

    }
}
