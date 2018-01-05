package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.management.MessageManager;
import com.expocalendar.project.web.service.SelectionService;
import com.expocalendar.project.web.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class SelectionCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<String> paramNames = Collections.list(request.getParameterNames());

        Map<String, String> requestParameters = new HashMap<>();

        for (String parameter : paramNames) {
            requestParameters.put(parameter, request.getParameter(parameter));
        }

        String page;

        if (request.getParameter("expositionId") != null) {
            Exposition exposition = SelectionService.getExposition(Integer.valueOf(request.getParameter("expositionId")));
            request.getSession().setAttribute("exposition", exposition);
            page = ConfigurationManager.getProperty("path.page.order");
        } else {
            Validator.validateSelectionParameters(requestParameters);
            setAttributes(request, requestParameters);
            page = ConfigurationManager.getProperty("path.page.main");

        }
        return page;
    }

    private void setAttributes(HttpServletRequest request, Map<String, String> requestParameters) {

        List<ExpoHall> expoHalls = SelectionService.getExpoHalls(requestParameters.get("city"));

        List<String> cities = SelectionService.findAllCities();

        List<String> themes = SelectionService.findThemes();

        List<Exposition> expositions = SelectionService.findExpositions(requestParameters);

        request.getSession().setAttribute("dateFrom", requestParameters.get("dateFrom"));
        request.getSession().setAttribute("dateTo", requestParameters.get("dateTo"));
        request.getSession().setAttribute("theme", requestParameters.get("theme"));
        request.getSession().setAttribute("hallId", requestParameters.get("hallId"));
        request.getSession().setAttribute("halls", expoHalls);
        request.getSession().setAttribute("themes", themes);
        request.getSession().setAttribute("cities", cities);
        request.getSession().setAttribute("expositions", expositions);

        if (expositions.isEmpty()) {
            request.setAttribute("nothingFound", MessageManager.getProperty("message.nothingFound"));
        }


    }

}
