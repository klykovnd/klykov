package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.interfaces.AdminService;
import com.expocalendar.project.web.service.ServiceFactory;
import com.expocalendar.project.web.service.interfaces.SelectionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.util.Map;

public class UpdateCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(UpdateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        AdminService adminService = serviceFactory.getAdminService();
        SelectionService selectionService = serviceFactory.getSelectionService();

        Map<String, String> requestParameters = ControllerHelper.getInstance().extractParameters(request);
        HttpSession session = request.getSession();

        String obj = request.getParameter("object");
        String page = null;

        switch (obj) {
            case "exposition":
                try {
                    adminService.updateExposition(requestParameters);
                    session.setAttribute("allExpositions", selectionService.getAllExpositions());
                    session.removeAttribute("updExposition");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                page = ConfigurationManager.getProperty("path.page.admin");
                break;
            case "hall":
                adminService.updateExpoHall(requestParameters);
                request.getSession().setAttribute("halls", selectionService.getExpoHalls());
                request.getSession().removeAttribute("updHall");
                page = ConfigurationManager.getProperty("path.page.admin");
                break;
            case "account":
                Account updAccount = (Account) session.getAttribute("account");
                Account account = serviceFactory.getAuthorizationService().updateAccount(requestParameters, updAccount);
                session.setAttribute("account", account);
                page = ConfigurationManager.getProperty("path.page.account");
        }

        LOGGER.info(this.getClass().getSimpleName() + " executed");

        return page;
    }
}
