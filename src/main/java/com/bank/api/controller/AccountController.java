package com.bank.api.controller;

import com.bank.api.entity.Account;
import com.bank.api.entity.User;
import com.bank.api.repository.AccountRepository;
import com.bank.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts/{id}")//просмотр всех счетов и баланса по userID
    public List<Account> showAllAccountsByUserId(@PathVariable long id){

        List<Account> allAccounts = accountService.getAllAccounts(id);
        return allAccounts;

    }

}
