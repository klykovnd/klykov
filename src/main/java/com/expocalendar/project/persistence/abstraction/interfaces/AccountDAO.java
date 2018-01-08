package com.expocalendar.project.persistence.abstraction.interfaces;

import com.expocalendar.project.entities.Account;


public interface AccountDAO {

    Account findAccount(String login, String password);

    void createAccount(Account account);

    boolean isExist(String login, String email);

    void saveOrder(Account account, int expoId, int remainder);
}
