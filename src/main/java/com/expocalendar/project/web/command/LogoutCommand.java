package com.expocalendar.project.web.command;

import com.expocalendar.project.web.management.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("account");
        return ConfigurationManager.getProperty("path.page.main");
    }
}
