package com.bank.api.controller;

import com.bank.api.entity.Contractor;
import com.bank.api.responses.MyResponse;
import com.bank.api.service.ContractorService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class ContractorController {

    @Autowired
    ContractorService contractorService;

    @GetMapping("/{id}/contractors")//просмотр всех контрагентов по userId
    public Set<Contractor> getAllContractorsByUserId(@PathVariable long id){

        Set<Contractor> allContractorsByUserId = contractorService.getAllContractorsByUserId(id);
        return allContractorsByUserId;

    }

    @PostMapping("/contractors")//добавить контрагента к юзеру через userId
    public ResponseEntity<MyResponse>  addContractor(@RequestBody JsonNode jsonNode){

        contractorService.saveContractor(jsonNode.get("userId").asLong(), jsonNode.get("contractorName").asText());
        return new ResponseEntity<>(new MyResponse(true), HttpStatus.OK);


    }

}
