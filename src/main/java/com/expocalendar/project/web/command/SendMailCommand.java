package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.entities.Order;
import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.ServiceFactory;
import com.expocalendar.project.web.service.interfaces.OrderService;
import com.expocalendar.project.web.service.interfaces.SelectionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;

public class SendMailCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(SendMailCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        SelectionService selectionService = serviceFactory.getSelectionService();
        OrderService orderService = serviceFactory.getOrderService();

        Map<String, String> requestParameters = ControllerHelper.getInstance().extractParameters(request);

        HttpSession session = request.getSession();

        Account account = (Account) session.getAttribute("account");


        ExpoHall expoHall = selectionService.getExpoHall(Integer.valueOf(requestParameters.get("hallId")));
        Map<Order, Exposition> orderMap = orderService.getOrders(account);

        Order order = new Order(requestParameters.get("orderKey"),
                Integer.valueOf(requestParameters.get("ticketsNumber")));

        Exposition exposition = orderMap.get(order);

        ServiceFactory.getInstance().getMailingService().sendMail(order, account, exposition, expoHall);

        LOGGER.info(this.getClass().getSimpleName() + " executed");

        return ConfigurationManager.getProperty("path.page.account");

    }
}
