package com.bank.api.repository;

import com.bank.api.entity.Account;
import com.bank.api.entity.Card;
import com.bank.api.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAllByUser(User user);

    Account findByAccountNumber(String s);

    Account findByCardsIsContaining(Card card);

}
