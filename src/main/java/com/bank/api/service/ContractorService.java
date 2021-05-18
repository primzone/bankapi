package com.bank.api.service;

import com.bank.api.entity.Contractor;
import com.bank.api.entity.User;
import com.bank.api.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractorService {
    @Autowired
    ContractorRepository contractorRepository;
    @Autowired
    UserService userService;

    public List<Contractor> getAllContractorsByUserId(long id) {

        Optional<User> byId = userService.findById(id);

        if (!byId.isPresent()) return new ArrayList<Contractor>();



        return byId.get().getContractors();
    }

    public void saveContractor(long userId, String contractorName) {

        Optional<User> byId = userService.findById(userId);

        if (byId.isPresent()){
            byId.get().addContractorToUser(new Contractor(contractorName, byId.get()));
            userService.saveUser(byId.get());
        }


    }


    public Optional<Contractor> findById(long contractorId) {
        return contractorRepository.findById(contractorId);
    }
}
