package com.expocalendar.project.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocalizationCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter("locale");
        request.getSession().setAttribute("locale", locale);
        return request.getParameter("page");
    }
}
