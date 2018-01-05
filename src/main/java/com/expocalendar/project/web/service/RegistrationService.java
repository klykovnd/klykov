package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.Map;

public class RegistrationService {
    private static final AccountDAO accountDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getAccountDAO();

    //TODO: use custom exceptions to message registration failed
    public static boolean checkAccount(Map<String, String> requestParameters) {
        String login = requestParameters.get("login");
        String password = requestParameters.get("password");
        String repeat = requestParameters.get("repeat");
        String email = requestParameters.get("email");
        return (password.equals(repeat)) && (!accountDAO.isExist(login, email));
    }

    //TODO: account builder or constructor
    public static void createAccount(Map<String, String> requestParameters) {
        Account account = new Account();
        account.setFirstName(requestParameters.get("firstName"));
        account.setLastName(requestParameters.get("lastName"));
        account.setLogin(requestParameters.get("login"));
        account.setPassword(requestParameters.get("password"));
        account.setEmail(requestParameters.get("email"));
        accountDAO.createAccount(account);
    }
}
