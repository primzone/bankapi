package com.bank.api.service;

import com.bank.api.entity.Contractor;
import com.bank.api.entity.User;
import com.bank.api.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service

public class ContractorService {
    @Autowired
    ContractorRepository contractorRepository;
    @Autowired
    UserService userService;

    public Set<Contractor> getAllContractorsByUserId(long id) {

        User user = userService.findById(id);

        return user.getContractors();
    }

    @Transactional
    public Contractor saveContractor(long userId, String contractorName) {

        //получаем юзера по айди
        User user = userService.findById(userId);
        //создаем нового контрагента
        Contractor contractor = new Contractor(contractorName, user);
        //сохраняем
        contractorRepository.save(contractor);

        return contractor;

    }


    public Optional<Contractor> findById(long contractorId) {
        return contractorRepository.findById(contractorId);
    }
}
