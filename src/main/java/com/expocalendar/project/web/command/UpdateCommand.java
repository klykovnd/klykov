package com.expocalendar.project.web.command;

import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.UpdateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.util.Map;

public class UpdateCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> requestParameters = ControllerHelper.extractParameters(request);

        try {
            UpdateService.getInstance().updateExposition(requestParameters);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ConfigurationManager.getProperty("path.page.admin");
    }
}
