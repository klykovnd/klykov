package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;

import java.util.*;

public class SelectionService {
    private static final ExpositionDAO expositionDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpositionDAO();
    private static final ExpoHallDAO expoHallDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpoHallDAO();

    private SelectionService() {
    }

    public static List<ExpoHall> getExpoHalls(String city) {
        return (city == null) ? (expoHallDAO.findAll()) : (expoHallDAO.findByCity(city));
    }

    public static List<String> findAllCities() {
        return expoHallDAO.findAllCities();
    }

    public static List<Exposition> findExpositions(Map<String, String> requestParameters) {
        String query = expositionDAO.parseQuery(requestParameters);
        return expositionDAO.findExpositions(query);
    }

    public static List<String> findThemes() {
        return expositionDAO.findThemes();
    }

    public static Exposition getExposition(Integer id) {
        return expositionDAO.findExposition(id);
    }
}
