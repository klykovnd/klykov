package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.ConfigurationManager;
import com.expocalendar.project.web.service.ServiceFactory;
import com.expocalendar.project.web.service.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LoginCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> requestParameters = ControllerHelper.getInstance().extractParameters(request);
        HttpSession session = request.getSession();

        String page;

        Account account = null;

        if (Validator.validAccountParameters(requestParameters)) {
            account = ServiceFactory.getInstance().getAuthorizationService().
                    findAccount(request.getParameter("login"),
                    request.getParameter("password"));
        }

        if (account != null) {
            page = ConfigurationManager.getProperty("path.page.main");
            session.removeAttribute("noSuchAccount");
            session.setAttribute("account", account);
        } else {
            page = ConfigurationManager.getProperty("path.page.login");
            session.setAttribute("noSuchAccount", new Object());
        }

        LOGGER.info(this.getClass().getSimpleName() + " executed");
        return page;
    }
}
