package com.expocalendar.project.persistence.abstraction.interfaces;


import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.entities.Order;

import java.util.Map;

public interface OrderDAO {

    Map<Order, Exposition> getOrders(int accountId);

    void saveOrder(String orderKey, int accountId, int expositionId, int ticketNumber, double remainder);

}
