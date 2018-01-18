package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.ServiceFactory;
import com.expocalendar.project.web.service.interfaces.OrderService;
import com.expocalendar.project.web.service.interfaces.SelectionService;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RedirectCommand implements ICommand {
    private static final String LOGIN = "/login";
    private static final String REGISTRATION = "/registration";
    private static final String ORDER = "/order";
    private static final String MAIN = "/main";
    private static final String INDEX = "/index";
    private static final String ADMIN = "/admin";
    private static final String ACCOUNT = "/account";

    private final static Logger LOGGER = Logger.getLogger(RedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        SelectionService selectionService = serviceFactory.getSelectionService();
        OrderService orderService = serviceFactory.getOrderService();

        HttpSession session = request.getSession();

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
                session.setAttribute("exposition", selectionService.
                        getExposition(Integer.valueOf(request.getParameter("expositionId"))));
                url = ConfigurationManager.getProperty("path.page.order");
                break;
            case MAIN:
                url = ConfigurationManager.getProperty("path.page.index");
                break;
            case INDEX:
                url = ConfigurationManager.getProperty("path.page.index");
                break;
            case ADMIN:
                session.setAttribute("allExpositions", selectionService.getAllExpositions());
                session.setAttribute("halls", selectionService.getExpoHalls());
                url = ConfigurationManager.getProperty("path.page.admin");
                break;
            case ACCOUNT:
                Account account = (Account) session.getAttribute("account");
                session.setAttribute("orders", orderService.getOrders(account));
                url = ConfigurationManager.getProperty("path.page.account");
                break;
        }

        LOGGER.info(this.getClass().getSimpleName() + " executed");

        return url;
    }
}
