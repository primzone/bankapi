package com.bank.api.controller;

import com.bank.api.entity.Card;
import com.bank.api.exception_handling.card_exceptions.NoSuchCardException;
import com.bank.api.exception_handling.user_exceptions.NoSuchUserException;
import com.bank.api.responses.MyResponse;
import com.bank.api.service.CardService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
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

        List<Card> allCardsFromUserId = cardService.getAllCardsFromUserId(id);

        if (allCardsFromUserId == null || allCardsFromUserId.size() == 0){
            throw new NoSuchCardException("No such cards with userId = " + id);
        }

        return allCardsFromUserId;

    }

    @PostMapping("/cards")//выпуск новой карты по счету
    public MyResponse addCardToAccount(@RequestBody ObjectNode jsonNodes){

        cardService.addCardToUser(jsonNodes.get("accountNumber").asText());
        return new MyResponse(true);
    }




    @PostMapping("/cards/refill")//пополнение карты (счета через карту)
    public MyResponse refillAccountByCard(@RequestBody ObjectNode jsonNodes){

       cardService.refillAccountByCard(jsonNodes.get("cardNumber").asText(), jsonNodes.get("amount").asDouble());

       return new MyResponse(true);
    }

    @PostMapping("/cards/transfer")//перевод контрагенту средств

    public MyResponse transferToContractorAccount(@RequestBody ObjectNode jsonNodes){

        cardService.transferToContractorAccount(jsonNodes.get("cardNumber").asText(),
                jsonNodes.get("contractorCardNumber").asText(),
                jsonNodes.get("amount").asDouble());

        return new MyResponse(true);

    }

}
