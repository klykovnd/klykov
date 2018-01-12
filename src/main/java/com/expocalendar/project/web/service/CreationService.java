package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;

public class CreationService {
    private ExpositionDAO expositionDAO;
    private static CreationService instance;

    private CreationService() {
        expositionDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpositionDAO();
    }

    public static CreationService getInstance() {
        if (instance == null) {
            instance = new CreationService();
        }
        return instance;
    }

    public void createExposition(Map<String, String> requestParameters) throws MalformedURLException {

        Exposition exposition = Exposition.newBuilder().setTitle(requestParameters.get("title")).
                setDateFrom(Date.valueOf(requestParameters.get("dateFrom"))).
                setDateTo(Date.valueOf(requestParameters.get("dateFrom"))).
                setTheme(requestParameters.get("theme")).
                setTicketPrice(Double.valueOf(requestParameters.get("price"))).
                setExpoHallId(Integer.valueOf(requestParameters.get("hallId"))).
                setDescription(requestParameters.get("description")).
                setBeginTime(Time.valueOf(requestParameters.get("beginTime") + ":00")).
                setPicture(new URL(requestParameters.get("url"))).build();

        expositionDAO.createExposition(exposition);
    }
}
