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

    private long transactionNumber;

    private boolean confirmation;
//

    public Transaction() {
    }

    public Transaction( long senderAccountNumberId, long recipientAccountNumberId, double amount) {
        this.senderAccountNumberId = senderAccountNumberId;
        this.recipientAccountNumberId = recipientAccountNumberId;
        this.amount = amount;
        this.confirmation = false;
    }

    public Transaction(long senderAccountNumberId, long recipientAccountNumberId, String senderCardNumber, String recipientCardNumber, double amount, long transactionNumber) {
        this.senderAccountNumberId = senderAccountNumberId;
        this.recipientAccountNumberId = recipientAccountNumberId;
        this.senderCardNumber = senderCardNumber;
        this.recipientCardNumber = recipientCardNumber;
        this.amount = amount;
        this.confirmation = false;
        this.transactionNumber = transactionNumber;
    }

    @OneToMany(mappedBy = "transaction")
    private List<Payment> payments;

    private long senderAccountNumberId;
    private long recipientAccountNumberId;

    private String senderCardNumber;
    private String recipientCardNumber;


    private double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public long getSenderAccountNumberId() {
        return senderAccountNumberId;
    }

    public void setSenderAccountNumberId(long senderAccountNumberId) {
        this.senderAccountNumberId = senderAccountNumberId;
    }

    public long getRecipientAccountNumberId() {
        return recipientAccountNumberId;
    }

    public void setRecipientAccountNumberId(long recipientAccountNumberId) {
        this.recipientAccountNumberId = recipientAccountNumberId;
    }

    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(String senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    public String getRecipientCardNumber() {
        return recipientCardNumber;
    }

    public void setRecipientCardNumber(String recipientCardNumber) {
        this.recipientCardNumber = recipientCardNumber;
    }

}
