package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.CreditCard;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;

import java.util.Map;

public class RegistrationService {
    private AccountDAO accountDAO;

    private static RegistrationService instance;

    private RegistrationService() {
        accountDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getAccountDAO();
    }

    public static RegistrationService getInstance() {
        if (instance == null) {
            instance = new RegistrationService();
        }
        return instance;
    }

    public boolean checkAccount(Map<String, String> requestParameters) {
        String login = requestParameters.get("login");
        String password = requestParameters.get("password");
        String repeat = requestParameters.get("repeat");
        String email = requestParameters.get("email");
        return (password.equals(repeat)) && (!accountDAO.isExist(login, email));
    }

    public void createAccount(Map<String, String> requestParameters) {
        Account account = new Account();
        account.setFirstName(requestParameters.get("firstName"));
        account.setLastName(requestParameters.get("lastName"));
        account.setLogin(requestParameters.get("login"));
        account.setPassword(requestParameters.get("password"));
        account.setEmail(requestParameters.get("email"));


        CreditCard creditCard = new CreditCard(requestParameters.get("cardNumber"),
                requestParameters.get("cvv"), requestParameters.get("cardHolder"),
                requestParameters.get("month"), requestParameters.get("year"));


        accountDAO.createAccount(account, creditCard);
    }
}
