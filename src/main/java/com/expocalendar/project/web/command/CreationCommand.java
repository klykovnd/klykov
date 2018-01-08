package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.CreationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        CreationService.getInstance().createExposition(ControllerHelper.extractParameters(request));

        return ConfigurationManager.getProperty("path.page.main");
    }
}
