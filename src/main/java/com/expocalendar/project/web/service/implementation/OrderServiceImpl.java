package com.expocalendar.project.web.service.implementation;

import com.expocalendar.project.entities.*;
import com.expocalendar.project.persistence.abstraction.interfaces.*;
import com.expocalendar.project.web.service.ServiceFactory;
import com.expocalendar.project.web.service.Validator;
import com.expocalendar.project.web.service.interfaces.OrderService;
import org.apache.log4j.*;

import java.text.SimpleDateFormat;
import java.util.*;


public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    private ExpositionDAO expositionDAO;
    private ExpoHallDAO expoHallDAO;
    private CreditCardDAO creditCardDAO;
    private OrderDAO orderDAO;


    public OrderServiceImpl(ExpositionDAO expositionDAO, ExpoHallDAO expoHallDAO,
                             CreditCardDAO creditCardDAO, OrderDAO orderDAO) {

        this.expositionDAO = expositionDAO;
        this.expoHallDAO = expoHallDAO;
        this.creditCardDAO = creditCardDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public void processOrder(Account account, Map<String, String> requestParameters) {

        int ticketsNumber = Integer.valueOf(requestParameters.get("number"));
        int expoId = Integer.valueOf(requestParameters.get("expoId"));

        Exposition exposition = expositionDAO.findExposition(expoId);
        ExpoHall expoHall = expoHallDAO.findExpoHall(exposition.getExpoHallId());
        CreditCard creditCard = creditCardDAO.findCard(account.getId());

        double withdraw = exposition.getTicketPrice() * ticketsNumber;

        if (creditCard != null && Validator.validCard(requestParameters, creditCard, withdraw)) {

            String orderKey = generateKey(account);
            double remainder = creditCard.getBalance() - withdraw;

            Order order = new Order(orderKey, ticketsNumber);

            orderDAO.saveOrder(orderKey, account.getId(), expoId, ticketsNumber, remainder);

            ServiceFactory.getInstance().getMailingService().sendMail(order, account, exposition, expoHall);
        }
    }

    @Override
    public Map<Order, Exposition> getOrders(Account account) {
        return orderDAO.getOrders(account.getId());
    }

    private String generateKey(Account account) {
        return new SimpleDateFormat("yyMMddhhmmssMs").format(new Date()) + account.getId();
    }

}
