package com.expocalendar.project.web.service.implementation;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.entities.Order;
import com.expocalendar.project.web.management.MessageManager;
import com.expocalendar.project.web.service.interfaces.MailingService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailingServiceImpl implements MailingService {
    private static final Logger LOGGER = Logger.getLogger(MailingServiceImpl.class);


    private Authenticator authenticator = new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("expocalendar2017@gmail.com", "92klykov");
        }
    };


    @Override
    public void sendMail(Order order, Account account, Exposition exposition, ExpoHall expoHall) {
        String msg = String.format(MessageManager.getProperty("message.mail.content"),
                account.getFirstName(), account.getLastName(), exposition.getTitle(),
                expoHall.getName(), exposition.getDateFrom(), exposition.getDateTo(),
                order.getOrderKey(), order.getTicketNumber());


        Session session = Session.getInstance(getProperties(), authenticator);

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

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return properties;
    }

}
