package com.expocalendar.project.entities;

import java.io.Serializable;

public class Order implements Serializable {
    private String orderKey;
    private int ticketsNumber;

    public Order(String orderKey, int ticketNumber) {
        this.orderKey = orderKey;
        this.ticketsNumber = ticketNumber;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public Order setOrderKey(String orderKey) {
        this.orderKey = orderKey;
        return this;
    }

    public int getTicketNumber() {
        return ticketsNumber;
    }

    public Order setTicketNumber(int ticketNumber) {
        this.ticketsNumber = ticketNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (ticketsNumber != order.ticketsNumber) return false;
        return orderKey != null ? orderKey.equals(order.orderKey) : order.orderKey == null;
    }

    @Override
    public int hashCode() {
        int result = orderKey != null ? orderKey.hashCode() : 0;
        result = 31 * result + ticketsNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderKey='" + orderKey + '\'' +
                ", ticketNumber=" + ticketsNumber +
                '}';
    }
}
