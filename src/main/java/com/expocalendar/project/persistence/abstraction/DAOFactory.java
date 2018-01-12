package com.expocalendar.project.persistence.abstraction;

import com.expocalendar.project.entities.CreditCard;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.CreditCardDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.persistence.implementation.mysql.MySQLDAOFactory;

public abstract class DAOFactory {

    public static final int MYSQL = 1;


    public abstract AccountDAO getAccountDAO();

    public abstract ExpoHallDAO getExpoHallDAO();

    public abstract ExpositionDAO getExpositionDAO();

    public abstract CreditCardDAO getCreditCardDAO();


    public static DAOFactory getDAOFactory(int factory) {
        DAOFactory daoFactory = null;

        switch (factory) {
            case MYSQL:
                daoFactory = new MySQLDAOFactory();
        }
        return daoFactory;
    }


}
