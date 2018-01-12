package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.management.MessageManager;
import com.expocalendar.project.web.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Account account = LoginService.getInstance().findAccount(login, password);

        String page;

        if (account != null) {
            page = ConfigurationManager.getProperty("path.page.main");
            request.getSession().setAttribute("account", account);
        } else {
            page = ConfigurationManager.getProperty("path.page.login");
            request.getSession().setAttribute("noAccount", MessageManager.getProperty("message.loginError"));
        }

        return page;
    }
}
