package com.bank.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private long id;

    private String cardNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;


    private boolean confirmation;

    public Card() {
    }

    public Card(String cardNumber, Account account) {
        this.cardNumber = cardNumber;
        this.account = account;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }
}
