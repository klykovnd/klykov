package com.expocalendar.project.web.controller;

import com.expocalendar.project.web.command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerHelper {

    private static ControllerHelper instance;
    private HashMap<String, ICommand> commandsMap = new HashMap<>();


    private ControllerHelper() {
        commandsMap.put("login", new LoginCommand());
        commandsMap.put("logout", new LogoutCommand());
        commandsMap.put("selection", new SelectionCommand());
        commandsMap.put("registration", new RegistrationCommand());
        commandsMap.put("order", new OrderCommand());
        commandsMap.put("localization", new LocalizationCommand());
        commandsMap.put("creation", new CreationCommand());
        commandsMap.put("showExposition", new ShowExpositionCommand());
        commandsMap.put("update", new UpdateCommand());
        commandsMap.put("delete", new DeleteCommand());
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;

    }

    public ICommand defineCommand(String commandKey) {
        return commandsMap.get(commandKey);
    }

    public static Map<String, String> extractParameters(HttpServletRequest request) {
        Map<String, String> requestParameters = new HashMap<>();

        List<String> parameters = Collections.list(request.getParameterNames());

        for (String param : parameters) {
            requestParameters.put(param, request.getParameter(param));
        }

        return requestParameters;
    }
}
