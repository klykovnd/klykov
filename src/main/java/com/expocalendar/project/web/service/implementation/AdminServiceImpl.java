package com.expocalendar.project.web.service.implementation;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.web.service.interfaces.AdminService;
import com.expocalendar.project.web.service.Validator;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;

public class AdminServiceImpl implements AdminService {
    private ExpositionDAO expositionDAO;
    private ExpoHallDAO expoHallDAO;


    public AdminServiceImpl(ExpositionDAO expositionDAO, ExpoHallDAO expoHallDAO) {
        this.expositionDAO = expositionDAO;
        this.expoHallDAO = expoHallDAO;
    }

    @Override
    public void createExposition(Map<String, String> requestParameters) throws MalformedURLException {
        Exposition exposition = buildExposition(requestParameters);
        expositionDAO.createExposition(exposition);
    }

    @Override
    public void updateExposition(Map<String, String> requestParameters) throws MalformedURLException {
        Exposition exposition = buildExposition(requestParameters);
        exposition.setId(Integer.valueOf(requestParameters.get("expositionId")));
        expositionDAO.updateExposition(exposition);

    }

    @Override
    public void deleteExposition(int expositionId) {
        expositionDAO.deleteExposition(expositionId);
    }

    @Override
    public void createExpoHall(Map<String, String> requestParameters) {
        ExpoHall expoHall = new ExpoHall(requestParameters.get("name"), requestParameters.get("address"));
        expoHallDAO.createExpoHall(expoHall);
    }

    @Override
    public void updateExpoHall(Map<String, String> requestParameters) {
        ExpoHall expoHall = new ExpoHall(Integer.valueOf(requestParameters.get("hallId")),
                requestParameters.get("name"), requestParameters.get("address"));
        expoHallDAO.updateExpoHall(expoHall);
    }

    @Override
    public void deleteExpoHall(int expoHallId) {
        expoHallDAO.deleteExpoHall(expoHallId);
    }


    private Exposition buildExposition(Map<String, String> requestParameters) throws MalformedURLException {
        Validator.validateTime(requestParameters);
        return Exposition.newBuilder().
                setTitle(requestParameters.get("title")).
                setDateFrom(Date.valueOf(requestParameters.get("dateFrom"))).
                setDateTo(Date.valueOf(requestParameters.get("dateTo"))).
                setTheme(requestParameters.get("theme")).
                setTicketPrice(Double.valueOf(requestParameters.get("price"))).
                setExpoHallId(Integer.valueOf(requestParameters.get("hallId"))).
                setDescription(requestParameters.get("description")).
                setBeginTime(Time.valueOf(requestParameters.get("beginTime"))).
                setPicture(new URL(requestParameters.get("url"))).build();
    }

}
