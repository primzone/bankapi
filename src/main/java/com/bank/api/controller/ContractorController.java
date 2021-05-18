package com.bank.api.controller;

import com.bank.api.entity.Contractor;
import com.bank.api.entity.User;
import com.bank.api.service.AccountService;
import com.bank.api.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class ContractorController {

    @Autowired
    ContractorService contractorService;

    @GetMapping("/contractors/{id}")//просмотр всех контрагентов по userId
    public Set<Contractor> getAllContractorsByUserId(@PathVariable long id){

        Set<Contractor> allContractorsByUserId = contractorService.getAllContractorsByUserId(id);
        return allContractorsByUserId;

    }

    @PostMapping("/contractors")//добавить контрагента к юзеру через userId
    public Contractor addContractor(@RequestBody Map<String, String> map){
        System.out.println(1);
        return contractorService.saveContractor(Long.parseLong(map.get("userId")), map.get("contractorName"));
    }

}
