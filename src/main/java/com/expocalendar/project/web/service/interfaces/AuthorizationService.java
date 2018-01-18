package com.expocalendar.project.web.service.interfaces;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.web.exceptions.RegistrationException;

import java.util.Map;

public interface AuthorizationService {

    void checkAccount(Map<String, String> requestParameters) throws RegistrationException;

    void createAccount(Map<String, String> requestParameters);

    Account updateAccount(Map<String, String> requestParameters, Account account);

    Account findAccount(String login, String password);
}
