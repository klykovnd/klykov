package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.SelectionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ShowExpositionCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> requestParameters = ControllerHelper.extractParameters(request);
        Exposition updExposition = SelectionService.getInstance().getExposition(Integer.valueOf(requestParameters.get("expositionId")));
        request.getSession().setAttribute("updExposition", updExposition);
        return ConfigurationManager.getProperty("path.page.admin");
    }
}
