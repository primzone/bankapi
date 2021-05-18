package com.bank.api.controller;

import com.bank.api.entity.Account;
import com.bank.api.entity.User;
import com.bank.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/users") //добавление нового юзера
    public User addUser(@RequestBody User user){
        employeeService.saveUser(user);
        return user;
    }

    @GetMapping("/users") // показать всех юзеров
    public List<User> showAllUsers(){

        List<User> allEmployees = employeeService.getAllUsers();
        return allEmployees;

    }


    @PostMapping("/accounts") //добавление нового счета для юзера
    public void addAccountToUser(@RequestBody Map<String, String> map){

        employeeService.addAccountToUser(Long.parseLong(map.get("userId")));

    }

    @PostMapping("/contractor/accounts") //добавление нового счета для контрагента
    public void addAccountToContractor(@RequestBody Map<String, String> map){

        employeeService.addAccountToContractor(Long.parseLong(map.get("contractorId")));

    }

    @PutMapping("/card") //потверждение новой карты
    public void confirmCard(@RequestBody Map<String, String> map){
        employeeService.confirmCard(map.get("cardNumber"));

    }

    @PutMapping("/transaction") //потверждение транзакции
    public void confirmTransaction(@RequestBody Map<String, String> map){
        employeeService.confirmTransaction(Long.parseLong(map.get("transactionNumber")));
    }

}
