package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void createExposition(Map<String, String> requestParameters){
        String from = requestParameters.get("from");
        String to = requestParameters.get("to");
        String name = requestParameters.get("title");
        String theme = requestParameters.get("theme");
        String hallId = requestParameters.get("hallId");
        String price = requestParameters.get("price");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Exposition exposition = new Exposition();

        try {
            exposition.setDateFrom(simpleDateFormat.parse(from));
            exposition.setDateTo(simpleDateFormat.parse(to));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        exposition.setTitle(name);
        exposition.setTheme(theme);
        exposition.setExpoHallId(Integer.valueOf(hallId));
        exposition.setTicketPrice(Integer.valueOf(price));

        expositionDAO.createExposition(exposition);
    }
}
