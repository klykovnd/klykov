package com.expocalendar.project.web.service.interfaces;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.entities.Order;

public interface MailingService {

    void sendMail(Order order, Account account, Exposition exposition, ExpoHall expoHall);
}
