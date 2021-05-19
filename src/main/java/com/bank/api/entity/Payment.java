package com.bank.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    @JsonIgnore
    private Transaction transaction;

    public Payment() {
    }

    public Payment(Account account) {
        this.account = account;
    }


    public Payment(Account account, Transaction transaction) {
        this.account = account;
        this.transaction = transaction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }


}
