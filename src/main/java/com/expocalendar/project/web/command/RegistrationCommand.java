package com.expocalendar.project.web.command;

import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.RegistrationService;
import com.expocalendar.project.web.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

public class RegistrationCommand implements ICommand {

    private RegistrationService registrationService = RegistrationService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> requestParameters = ControllerHelper.extractParameters(request);

        String page;

        if (Validator.validAccountParameters(requestParameters) && registrationService.checkAccount(requestParameters)) {
            registrationService.createAccount(requestParameters);
            page = ConfigurationManager.getProperty("path.page.login");
            request.setAttribute("regSuccess", "Congrats! Now you can login");
        } else {
            page = ConfigurationManager.getProperty("path.page.registration");
            request.setAttribute("regFail", "Registration Failed");
        }
        return page;
    }
}
