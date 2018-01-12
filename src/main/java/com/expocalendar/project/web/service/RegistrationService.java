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
        Account account = Account.newBuilder().setFirstName(requestParameters.get("firstName")).
                setLastName(requestParameters.get("lastName")).
                setLogin(requestParameters.get("login")).
                setPassword(requestParameters.get("password")).
                setPassword(requestParameters.get("email")).build();

        CreditCard creditCard = CreditCard.newBuilder().setNumber(requestParameters.get("cardNumber")).
                setCVV(Integer.valueOf(requestParameters.get("cvv"))).
                setHolder(requestParameters.get("cardHolder")).
                setMonth(Integer.valueOf(requestParameters.get("month"))).
                setYear(Integer.valueOf(requestParameters.get("year"))).build();


        accountDAO.createAccount(account, creditCard);
    }
}
