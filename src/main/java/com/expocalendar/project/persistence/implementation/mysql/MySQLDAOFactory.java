package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.persistence.abstraction.interfaces.*;
import com.expocalendar.project.persistence.abstraction.DAOFactory;

public class MySQLDAOFactory extends DAOFactory {


    /**
     * @return instance of concrete AccountDAO implementation.
     */
    @Override
    public AccountDAO getAccountDAO() {
        return MySQLAccountDAO.getInstance();
    }

    /**
     * @return instance of concrete ExpoHallDAO implementation.
     */
    @Override
    public ExpoHallDAO getExpoHallDAO() {
        return MySQLExpoHallDAO.getInstance();
    }

    /**
     * @return instance of concrete ExpositionDAO implementation.
     */
    @Override
    public ExpositionDAO getExpositionDAO() {
        return MySQLExpositionDAO.getInstance();
    }

    /**
     * @return instance of concrete CreditCardDAO implementation.
     */
    @Override
    public CreditCardDAO getCreditCardDAO() {
        return MySQLCreditCardDAO.getInstance();
    }

    /**
     * @return instance of concrete OrderDAO implementation.
     */
    @Override
    public OrderDAO getOrderDAO() {
        return MySQLOrderDAO.getInstance();
    }
}
