package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.ServiceFactory;
import com.expocalendar.project.web.service.interfaces.SelectionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ShowCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(ShowCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> requestParameters = ControllerHelper.getInstance().extractParameters(request);

        SelectionService selectionService = ServiceFactory.getInstance().getSelectionService();

        String obj = request.getParameter("object");

        switch (obj) {
            case "exposition":
                Exposition updExposition = selectionService.getExposition(Integer.valueOf(requestParameters.get("expositionId")));
                request.getSession().setAttribute("updExposition", updExposition);
                break;
            case "hall":
                ExpoHall expoHall = selectionService.getExpoHall(Integer.valueOf(requestParameters.get("hallId")));
                request.getSession().setAttribute("updHall", expoHall);
                break;
        }

        LOGGER.info(this.getClass().getSimpleName() + " executed");

        return ConfigurationManager.getProperty("path.page.admin");
    }
}
