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

    @Column(unique = true)
    private String accountNumber;

    private double balance;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contractor_id", nullable = true)
    @JsonIgnore
    private Contractor contractor;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    @JsonIgnore
    private List<Card> cards;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
    @JsonIgnore
    private List<Payment> payments;

    public void addPaymentToAccount(Payment payment){
        if (payments == null){
            payments = new ArrayList<>();
        }
        payments.add(payment);
        payment.setAccount(this);
    }


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

    public Account(String accountNumber, Contractor contractor) {
        this.accountNumber = accountNumber;
        this.contractor = contractor;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

}
