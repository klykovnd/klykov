package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;

public class UpdateService {
    private ExpositionDAO expositionDAO;
    private ExpoHallDAO expoHallDAO;

    private static UpdateService instance;

    private UpdateService() {
        expositionDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpositionDAO();
        expoHallDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpoHallDAO();
    }

    public static UpdateService getInstance() {
        if (instance == null) {
            instance = new UpdateService();
        }
        return instance;
    }

    public void updateExposition(Map<String, String> requestParameters) throws MalformedURLException {
        Exposition exposition = Exposition.newBuilder().
                setId(Integer.valueOf(requestParameters.get("expositionId"))).
                setTitle(requestParameters.get("title")).
                setDateFrom(Date.valueOf(requestParameters.get("dateFrom"))).
                setDateTo(Date.valueOf(requestParameters.get("dateTo"))).
                setTheme(requestParameters.get("theme")).
                setTicketPrice(Double.valueOf(requestParameters.get("price"))).
                setExpoHallId(Integer.valueOf(requestParameters.get("hallId"))).
                setDescription(requestParameters.get("description")).
                setBeginTime(Time.valueOf(requestParameters.get("beginTime")+":00")).
                setPicture(new URL(requestParameters.get("url"))).build();

        expositionDAO.updateExposition(exposition);

    }

}
