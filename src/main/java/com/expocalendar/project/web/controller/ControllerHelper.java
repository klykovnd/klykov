package com.expocalendar.project.web.controller;

import com.expocalendar.project.web.command.*;

import java.util.HashMap;

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
        commandsMap.put("creation",new CreationCommand());
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
}
