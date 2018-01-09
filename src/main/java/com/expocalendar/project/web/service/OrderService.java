package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.CreditCard;
import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.DAOFactory;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.web.management.MessageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderService.class);
    private static OrderService instance;

    private AccountDAO accountDAO;
    private ExpositionDAO expositionDAO;
    private ExpoHallDAO expoHallDAO;


    private OrderService() {
        accountDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getAccountDAO();
        expositionDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpositionDAO();
        expoHallDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExpoHallDAO();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public void processOrder(Account account, Map<String, String> requestParameters) {

        int ticketNumber = Integer.valueOf(requestParameters.get("number"));
        int expoId = Integer.valueOf(requestParameters.get("expoId"));

        Exposition exposition = expositionDAO.findExposition(expoId);
        ExpoHall expoHall = expoHallDAO.findExpoHall(exposition.getExpoHallId());
        CreditCard creditCard = accountDAO.findCard(account);

        int withdraw = exposition.getTicketPrice() * ticketNumber;

        if (creditCard != null && Validator.validCard(requestParameters, creditCard, withdraw)) {
            int remainder = creditCard.getBalance() - withdraw;
            accountDAO.saveOrder(account, expoId, remainder);
            sendMail(account, exposition, expoHall);
        }
    }


    private void sendMail(Account account, Exposition exposition, ExpoHall expoHall) {
        String msg = String.format(MessageManager.getProperty("message.mail.content"),
                account.getFirstName(), account.getLastName(), exposition.getTitle(),
                expoHall.getName(), exposition.getDateFrom(), exposition.getDateTo());


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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(account.getEmail()));
            message.setSubject("Tickets Order");
            message.setText(msg);
            Transport.send(message);

        } catch (AddressException e) {
            LOGGER.log(Level.DEBUG, "AddressException", e);
        } catch (MessagingException m) {
            LOGGER.log(Level.DEBUG, "MessagingException", m);
        }
    }
}
