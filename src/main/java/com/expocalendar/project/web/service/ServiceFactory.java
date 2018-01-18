package com.expocalendar.project.web.service;

import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.web.service.implementation.*;
import com.expocalendar.project.web.service.interfaces.*;

public class ServiceFactory {

    private static ServiceFactory instance;

    private DAOFactory daoFactory;

    private ServiceFactory() {
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    }


    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }


    public AdminService getAdminService() {
        return new AdminServiceImpl(daoFactory.getExpositionDAO(), daoFactory.getExpoHallDAO());
    }

    public AuthorizationService getAuthorizationService() {
        return new AuthorizationServiceImpl(daoFactory.getAccountDAO());
    }

    public MailingService getMailingService() {
        return new MailingServiceImpl();
    }

    public OrderService getOrderService() {
        return new OrderServiceImpl(daoFactory.getExpositionDAO(),
                daoFactory.getExpoHallDAO(),
                daoFactory.getCreditCardDAO(),
                daoFactory.getOrderDAO());
    }

    public SelectionService getSelectionService() {
        return new SelectionServiceImpl(daoFactory.getExpositionDAO(), daoFactory.getExpoHallDAO());
    }
}
