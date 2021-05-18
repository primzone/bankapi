package com.bank.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Contractor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private long id;

    private String name;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;


    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name = "users_contractors",
            joinColumns = @JoinColumn(name = "contractor_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();


    @OneToMany(mappedBy = "contractor")
    @JsonIgnore
    private List<Account> accounts;

    public void addAccountToContractor(Account account){
        if (accounts == null){
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        account.setContractor(this);
    }

    public Contractor() {
    }

    public Contractor(String name, User user) {
        this.name = name;
        this.users.add(user);
    }

    public void addUserToContractor(User user){
        if (users == null){
            users = new HashSet<>();
        }
        users.add(user);
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
