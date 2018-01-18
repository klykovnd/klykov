package com.expocalendar.project.persistence.abstraction.interfaces;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.CreditCard;


public interface AccountDAO {

    Account findAccount(String login, String password);

    void createAccount(Account account, CreditCard creditCard);

    boolean isExist(String login);

    void updateAccount(Account account);

}
