package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class OrderService {
    private final static String MSG_SUBJECT = "Tickets Order";
    private static final Logger LOGGER = Logger.getLogger(OrderService.class);
    private static final AccountDAO accountDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getAccountDAO();
    private static final ExpositionDAO expositionDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpositionDAO();
    private static StringBuilder stringBuilder;

    private OrderService() {
    }

    public static void processOrder(Account account, int expoId, int ticketNumber) {
        accountDAO.saveOrder(account, expoId);
        Exposition exposition = expositionDAO.findExposition(expoId);
        stringBuilder = new StringBuilder();
        stringBuilder.append(account);
        stringBuilder.append(exposition.toString());
        send(account.getEmail(), MSG_SUBJECT, stringBuilder.toString());
    }

    public static void processOrder(String recipient, int expoId) {
        Exposition exposition = expositionDAO.findExposition(expoId);
        stringBuilder = new StringBuilder();
        stringBuilder.append(exposition.toString());
        send(recipient, MSG_SUBJECT, stringBuilder.toString());
    }


    private static void send(String to, String sub, String msg) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("expocalendar2017@gmail.com", "92klykov");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("expocalendar2017@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            Transport.send(message);

        } catch (AddressException e) {
            LOGGER.log(Level.DEBUG, "AddressException", e);
        } catch (MessagingException m) {
            LOGGER.log(Level.DEBUG, "MessagingException", m);
        }
    }
}
