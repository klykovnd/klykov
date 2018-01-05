package com.expocalendar.project.web.controller;

import com.expocalendar.project.web.command.ICommand;
import com.expocalendar.project.web.command.RedirectCommand;
import com.expocalendar.project.web.management.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;

        try {

            ICommand command = ControllerHelper.getInstance().defineCommand(request.getParameter("command"));

            if (command == null) {
                command = new RedirectCommand();
            }

            page = command.execute(request, response);

        } catch (Exception e) {
            page = ConfigurationManager.getProperty("path.page.error");
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
