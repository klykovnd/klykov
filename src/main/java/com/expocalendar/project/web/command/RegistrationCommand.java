package com.expocalendar.project.web.command;

import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.RegistrationService;
import com.expocalendar.project.web.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<String> paramNames = Collections.list(request.getParameterNames());

        Map<String, String> requestParameters = new HashMap<>();

        for (String param : paramNames) {
            requestParameters.put(param, request.getParameter(param));
        }

        String page;

        if (Validator.validAccountParameters(requestParameters) && RegistrationService.checkAccount(requestParameters)) {
            RegistrationService.createAccount(requestParameters);
            page = ConfigurationManager.getProperty("path.page.login");
            request.getSession().setAttribute("regSuccess", "Congrats! Now you can login");
        } else {
            page = ConfigurationManager.getProperty("path.page.registration");
            request.getSession().setAttribute("regFail", "Registration Failed");
        }
        return page;
    }
}
