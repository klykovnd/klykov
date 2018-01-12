package com.expocalendar.project.web.command;


import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.CreationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;


public class CreationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            CreationService.getInstance().createExposition(ControllerHelper.extractParameters(request));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ConfigurationManager.getProperty("path.page.main");
    }
}
