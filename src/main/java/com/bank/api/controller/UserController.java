package com.bank.api.controller;

import com.bank.api.entity.User;
import com.bank.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/users")
    public List<User> showAllUsers(){

        List<User> allEmployees = userService.getAllUsers();
        return allEmployees;
    }

}
