package com.bank.api.controller;

import com.bank.api.entity.User;
import com.bank.api.responses.MyResponse;
import com.bank.api.service.EmployeeService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/users") //добавление нового юзера
    public MyResponse addUser(@RequestBody User user){
        employeeService.saveUser(user);
        return new MyResponse(true);
    }

    @GetMapping("/users") // показать всех юзеров
    public List<User> showAllUsers(){

        List<User> allEmployees = employeeService.getAllUsers();
        return allEmployees;

    }

    @PostMapping("/accounts") //добавление нового счета для юзера
    public MyResponse addAccountToUser(@RequestBody ObjectNode jsonNodes){

        employeeService.addAccountToUser(jsonNodes.get("userId").asLong());
        return new MyResponse(true);
    }

    @PostMapping("/contractor/accounts") //добавление нового счета для контрагента
    public MyResponse addAccountToContractor(@RequestBody ObjectNode jsonNode){

        employeeService.addAccountToContractor(jsonNode.get("contractorId").asLong());
        return new MyResponse(true);
    }

    @PutMapping("/card") //потверждение новой карты
    public MyResponse confirmCard(@RequestBody ObjectNode jsonNode){
        employeeService.confirmCard(jsonNode.get("cardNumber").asText());
        return new MyResponse(true);
    }

    @PutMapping("/transaction") //потверждение транзакции
    public MyResponse confirmTransaction(@RequestBody ObjectNode jsonNodes){
        employeeService.confirmTransaction(jsonNodes.get("transactionNumber").asLong());
        return new MyResponse(true);
    }

}
