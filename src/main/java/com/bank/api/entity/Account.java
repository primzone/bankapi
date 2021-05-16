package com.bank.api.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String accountNumber;

    private long balance;


    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    @JsonIgnore
    private List<Card> cards;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name = "account_transaction"
            , joinColumns = {@JoinColumn(name = "account_id"), @JoinColumn(name = "account_id")}
            , inverseJoinColumns = {@JoinColumn(name = "senderAccount_id"), @JoinColumn(name = "recipientAccount_id") }
    )
    private Set<Transaction> transactions = new HashSet<>();






    public void addCardToAccount(Card card){
        if (cards == null){
            cards = new ArrayList<>();
        }
        cards.add(card);
        card.setAccount(this);
    }


    public Account() {
    }

    public Account(String accountNumber, User user) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.balance = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
