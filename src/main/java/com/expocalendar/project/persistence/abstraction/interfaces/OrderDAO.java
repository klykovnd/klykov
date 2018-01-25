package com.expocalendar.project.persistence.abstraction.interfaces;


import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.entities.Order;

import java.util.Map;

public interface OrderDAO {

    Map<Order, Exposition> getOrders(int accountId);

    boolean saveOrder(Order order, Exposition exposition, int accountId, double remainder);

}
