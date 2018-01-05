package com.expocalendar.project.entities;

import java.io.Serializable;
import java.util.Date;

public class Exposition implements Serializable {
    private int id;
    private String name;
    private String theme;
    private Date dateFrom;
    private Date dateTo;
    private int ticketPrice;
    private int expoHallId;

    public Exposition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getExpoHallId() {
        return expoHallId;
    }

    public void setExpoHallId(int expoHallId) {
        this.expoHallId = expoHallId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exposition that = (Exposition) o;

        if (id != that.id) return false;
        if (Double.compare(that.ticketPrice, ticketPrice) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (theme != null ? !theme.equals(that.theme) : that.theme != null) return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        return dateTo != null ? dateTo.equals(that.dateTo) : that.dateTo == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        temp = Double.doubleToLongBits(ticketPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                ", startDate=" + dateFrom +
                ", endDate=" + dateTo +
                '}';
    }
}
