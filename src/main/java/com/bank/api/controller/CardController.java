package com.bank.api.controller;

import com.bank.api.entity.Account;
import com.bank.api.entity.Card;
import com.bank.api.entity.User;
import com.bank.api.service.CardService;
import com.bank.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public void addCardToAccount(@RequestBody Map<String,String> map){

        cardService.addCardToUser(map.get("accountNumber"));
    }

    @PostMapping("/cards/refill")//пополнение карты (счета через карту)
    public void refillAccountByCard(@RequestBody Map<String,String> map){

        cardService.refillAccountByCard(map.get("cardNumber"), Double.parseDouble(map.get("amount")));
    }


    @PostMapping("/cards/transfer")//перевод контрагенту средств
    public void transferToContractorAccount(@RequestBody Map<String,String> map){

        cardService.transferToContractorAccount(map.get("cardNumber"),
                map.get("contractorCardNumber"),
                Double.parseDouble(map.get("amount")));

    }

}
