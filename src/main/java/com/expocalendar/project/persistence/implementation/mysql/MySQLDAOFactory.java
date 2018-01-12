package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.CreditCardDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public AccountDAO getAccountDAO() {
        return MySQLAccountDAO.getInstance();
    }

    @Override
    public ExpoHallDAO getExpoHallDAO() {
        return MySQLExpoHallDAO.getInstance();
    }

    @Override
    public ExpositionDAO getExpositionDAO() {
        return MySQLExpositionDAO.getInstance();
    }

    @Override
    public CreditCardDAO getCreditCardDAO() {
        return MySQLCreditCardDAO.getInstance();
    }
}
