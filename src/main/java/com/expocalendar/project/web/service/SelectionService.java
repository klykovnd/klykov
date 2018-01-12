package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;

import java.util.*;

public class SelectionService {
    private ExpositionDAO expositionDAO;
    private ExpoHallDAO expoHallDAO;

    private static SelectionService instance;

    private SelectionService() {
        expositionDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpositionDAO();
        expoHallDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpoHallDAO();
    }

    public static SelectionService getInstance() {
        if (instance == null) {
            instance = new SelectionService();
        }
        return instance;
    }

    public List<ExpoHall> getExpoHalls() {
        return expoHallDAO.findAll();
    }

    public List<Exposition> findExpositions(Map<String, String> requestParameters, int limit, int offset) {
        String query = expositionDAO.parseQuery(requestParameters, limit,offset);
        return expositionDAO.findExpositions(query);
    }

    public int getNumberOfExpositions(Map<String, String> requestParameters) {
        String countQuery = expositionDAO.countQuery(requestParameters);
        return expositionDAO.countExpositions(countQuery);
    }

    public List<String> findThemes() {
        return expositionDAO.findThemes();
    }

    public Exposition getExposition(Integer id) {
        return expositionDAO.findExposition(id);
    }
}
