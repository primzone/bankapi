package com.bank.api.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Contractor> contractors;


    public void addAccountToUser(Account account){
        if (accounts == null){
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        account.setUser(this);
    }

    public void addContractorToUser(Contractor contractor){
        if (accounts == null){
            accounts = new ArrayList<>();
        }
        contractors.add(contractor);
        contractor.setUser(this);
    }

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.id = 0;
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }
}
