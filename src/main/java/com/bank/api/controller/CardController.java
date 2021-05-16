package com.bank.api.controller;

import com.bank.api.entity.Account;
import com.bank.api.entity.Card;
import com.bank.api.entity.User;
import com.bank.api.service.CardService;
import com.bank.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping("/cards/{id}")//просмотр списка карт по id
    public List<Card> getAllCards(@PathVariable long id){

        return cardService.getAllCardsFromUserId(id);

    }

    @PostMapping("/cards")//выпуск новой карты по счету
    public void addCardToAccount(@RequestBody Account account){

        cardService.addCardToUser(account.getAccountNumber());

    }

//    @PostMapping("/cards/refill")
//    public void addCardToAccount(@RequestBodyParam Model model){
//
//        System.out.println(model);
//       // cardService.addCardToUser(account.getAccountNumber());
//
//    }

}
