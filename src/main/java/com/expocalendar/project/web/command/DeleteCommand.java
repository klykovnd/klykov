package com.expocalendar.project.web.command;

import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.interfaces.AdminService;
import com.expocalendar.project.web.service.ServiceFactory;
import com.expocalendar.project.web.service.interfaces.SelectionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DeleteCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(DeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        AdminService adminService = serviceFactory.getAdminService();
        SelectionService selectionService = serviceFactory.getSelectionService();

        Map<String, String> requestParameters = ControllerHelper.getInstance().extractParameters(request);

        String obj = request.getParameter("object");

        switch (obj) {
            case "exposition":
                adminService.deleteExposition(Integer.valueOf(requestParameters.get("expositionId")));
                request.getSession().setAttribute("allExpositions", selectionService.getAllExpositions());
                request.getSession().removeAttribute("updExposition");
                break;

            case "hall":
                adminService.deleteExpoHall(Integer.valueOf(requestParameters.get("hallId")));
                request.getSession().setAttribute("halls", selectionService.getExpoHalls());
                request.getSession().removeAttribute("updHall");
        }

        LOGGER.info(this.getClass().getSimpleName() + " executed");

        return ConfigurationManager.getProperty("path.page.admin");
    }
}
