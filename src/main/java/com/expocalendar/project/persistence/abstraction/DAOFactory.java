package com.expocalendar.project.persistence.abstraction;


import com.expocalendar.project.persistence.abstraction.interfaces.*;
import com.expocalendar.project.persistence.implementation.mysql.MySQLDAOFactory;

public abstract class DAOFactory {

    public static final int MYSQL = 1;


    public abstract AccountDAO getAccountDAO();

    public abstract ExpoHallDAO getExpoHallDAO();

    public abstract ExpositionDAO getExpositionDAO();

    public abstract CreditCardDAO getCreditCardDAO();

    public abstract OrderDAO getOrderDAO();


    public static DAOFactory getDAOFactory(int factory) {
        DAOFactory daoFactory = null;

        switch (factory) {
            case MYSQL:
                daoFactory = new MySQLDAOFactory();
        }
        return daoFactory;
    }


}
