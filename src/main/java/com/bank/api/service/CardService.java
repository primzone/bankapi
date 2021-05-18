package com.bank.api.service;

import com.bank.api.entity.*;
import com.bank.api.repository.CardRepository;
import com.bank.api.utils.Utils;
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

       // cardRepository.fi
        Optional<User> byId = userService.findById(id);
        List<Card> cardList = new ArrayList<>();
        if (!byId.isPresent()) return new ArrayList<Card>();

        User user = byId.get();
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
        accountService.save(byAccountNumber);

    }

    public void refillAccountByCard(String cardNumber, Double amount) {

        //находим счет по номеру карты
        Account byAccountNumber = accountService.findByCardsIsContaining(cardRepository.findByCardNumber(cardNumber));
        //добавляем к балансу
        byAccountNumber.setBalance(byAccountNumber.getBalance() + amount);
        //сохраняем
        accountService.save(byAccountNumber);
    }

    @Transactional
    public void transferToContractorAccount(String cardNumber, String contractorCardNumber, double amount) {

        //находим счета отправителя и получателя
        Account senderAccount = cardRepository.findByCardNumber(cardNumber).getAccount();
        Account recipientAccount = cardRepository.findByCardNumber(contractorCardNumber).getAccount();

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
        return cardRepository.findByCardNumber(cardNumber);
    }

    public void save(Card byCardNumber) {
        cardRepository.save(byCardNumber);
    }
}
