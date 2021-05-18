package com.bank.api.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Contractor> contractors;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_contractors",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "contractor_id")
    )
    private Set<Contractor> contractors = new HashSet<>();



    public void addAccountToUser(Account account){
        if (accounts == null){
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        account.setUser(this);
    }




    public void addContractorToUser(Contractor contractor){

        contractors.add(contractor);
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

    public Set<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(Set<Contractor> contractors) {
        this.contractors = contractors;
    }
}
