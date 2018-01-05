package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.web.management.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> requestParameters = new HashMap<>();

        List<String> parameters = Collections.list(request.getParameterNames());

        for (String param : parameters) {
            requestParameters.put(param, request.getParameter(param));
        }

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
            exposition.setName(name);
            exposition.setTheme(theme);
            exposition.setExpoHallId(Integer.valueOf(hallId));
            exposition.setTicketPrice(Integer.valueOf(price));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        ExpositionDAO expositionDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpositionDAO();
        expositionDAO.createExposition(exposition);


        return ConfigurationManager.getProperty("path.page.main");
    }
}
