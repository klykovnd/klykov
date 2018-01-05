package com.expocalendar.project.web.command;

import com.expocalendar.project.web.management.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectCommand implements ICommand {
    private static final String LOGIN = "/login";
    private static final String REGISTRATION = "/registration";
    private static final String ORDER = "/order";
    private static final String MAIN = "/main";
    private static final String INDEX = "/index";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userPath = request.getServletPath();

        String url = ConfigurationManager.getProperty("path.page.error");
        switch (userPath) {
            case LOGIN:
                url = ConfigurationManager.getProperty("path.page.login");
                break;
            case REGISTRATION:
                url = ConfigurationManager.getProperty("path.page.registration");
                break;
            case ORDER:
                url = ConfigurationManager.getProperty("path.page.order");
                break;
            case MAIN:
                url = ConfigurationManager.getProperty("path.page.main");
                break;
            case INDEX:
                url = ConfigurationManager.getProperty("path.page.index");
        }
        return url;
    }
}
