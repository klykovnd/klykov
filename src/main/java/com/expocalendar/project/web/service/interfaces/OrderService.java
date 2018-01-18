package com.expocalendar.project.web.service.interfaces;

import com.expocalendar.project.entities.*;


import java.util.Map;

public interface OrderService {
    void processOrder(Account account, Map<String, String> requestParameters);

    Map<Order, Exposition> getOrders(Account account);
}
