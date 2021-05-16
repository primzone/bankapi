package com.bank.api.service;

import com.bank.api.entity.User;
import com.bank.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }


    public void addUser(User user) {
        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
}
