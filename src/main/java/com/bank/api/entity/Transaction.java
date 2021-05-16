package com.bank.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @GeneratedValue(strategy= GenerationType.AUTO)
    private String transactionNumber;


    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name = "account_transaction"
            , joinColumns = {@JoinColumn(name = "senderAccount_id"), @JoinColumn(name = "recipientAccount_id")}
            , inverseJoinColumns = {@JoinColumn(name = "account_id"), @JoinColumn(name = "account_id")}
    )
    private Set<Account> accounts = new HashSet<>();





    private String SenderAccountNumber;
    private String recipientAccountNumber;


}
