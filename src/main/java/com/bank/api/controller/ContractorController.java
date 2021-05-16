package com.bank.api.controller;

import com.bank.api.entity.Contractor;
import com.bank.api.entity.User;
import com.bank.api.service.AccountService;
import com.bank.api.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class ContractorController {

    @Autowired
    ContractorService contractorService;


    @GetMapping("/contractors/{id}")
    public List<Contractor> getAllContractorsByUserId(@PathVariable long id){

        List<Contractor> allContractorsByUserId = contractorService.getAllContractorsByUserId(id);
        return allContractorsByUserId;

    }

    @PostMapping("/contractors")
    public Contractor addUser(@RequestBody Map<String, String> map){

        contractorService.saveContractor(Long.parseLong(map.get("userId")), map.get("contractorName"));
      //  return contractor;
        return new Contractor();

    }



}
