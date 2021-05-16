package com.bank.api.service;

import com.bank.api.entity.Account;
import com.bank.api.entity.Card;
import com.bank.api.entity.User;
import com.bank.api.repository.CardRepository;
import com.bank.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;


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
}
