package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.web.controller.ControllerHelper;
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
        Map<String, String> requestParameters = ControllerHelper.extractParameters(request);

        String page;

        if (request.getParameter("expositionId") != null) {
            Exposition exposition = SelectionService.getInstance().
                    getExposition(Integer.valueOf(request.getParameter("expositionId")));
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

        int numberOfExpositions = SelectionService.getInstance().getNumberOfExpositions(requestParameters);
        int page = 1;
        int recordsPerPage = 3;
        int numberOfPages = (int) Math.ceil(numberOfExpositions * 1.0 / recordsPerPage);

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }


        List<ExpoHall> expoHalls = SelectionService.getInstance().getExpoHalls();
        List<String> themes = SelectionService.getInstance().findThemes();
        List<Exposition> expositions = SelectionService.getInstance().findExpositions(requestParameters,
                recordsPerPage, (page - 1) * recordsPerPage);


        request.getSession().setAttribute("numberOfPages", numberOfPages);
        request.getSession().setAttribute("currentPage", page);
        request.getSession().setAttribute("dateFrom", requestParameters.get("dateFrom"));
        request.getSession().setAttribute("dateTo", requestParameters.get("dateTo"));
        request.getSession().setAttribute("theme", requestParameters.get("theme"));
        request.getSession().setAttribute("hallId", requestParameters.get("hallId"));
        request.getSession().setAttribute("halls", expoHalls);
        request.getSession().setAttribute("themes", themes);

        request.getSession().setAttribute("expositions", expositions);

        if (expositions.isEmpty()) {
            request.setAttribute("nothingFound", MessageManager.getProperty("message.nothingFound"));
        }
    }

}
