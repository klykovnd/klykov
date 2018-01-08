package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String ticketNumber = request.getParameter("number");
        String expoId = request.getParameter("expoId");

        Account account = (Account) request.getSession().getAttribute("account");

        OrderService.getInstance().processOrder(account, Integer.valueOf(expoId), Integer.valueOf(ticketNumber));

        return ConfigurationManager.getProperty("path.page.main");

    }
}
