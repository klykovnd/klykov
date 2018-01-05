package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;

public class LoginService {
    private static final AccountDAO accountDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getAccountDAO();

    public static Account findAccount(String login, String password) {
        return accountDAO.findAccount(login, password);
    }
}
