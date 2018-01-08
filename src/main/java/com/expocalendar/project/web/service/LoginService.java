package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;

public class LoginService {
    private AccountDAO accountDAO;
    private static LoginService instance;

    private LoginService() {
        accountDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getAccountDAO();
    }

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }


    public Account findAccount(String login, String password) {
        return accountDAO.findAccount(login, password);
    }
}
