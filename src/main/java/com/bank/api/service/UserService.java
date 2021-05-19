package com.bank.api.service;

import com.bank.api.entity.User;
import com.bank.api.exception_handling.user_exceptions.NoSuchUserException;
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

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findById(long id) {

        if (!userRepository.findById(id).isPresent()){
            throw new NoSuchUserException("User by id = " + id + " not found");
        }

        return userRepository.findById(id).get();
    }
}
