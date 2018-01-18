package com.expocalendar.project.entities;

import java.io.Serializable;
import java.net.URL;
import java.sql.Time;
import java.sql.Date;

public class Exposition implements Serializable {
    private int id;
    private String title;
    private String theme;
    private Date dateFrom;
    private Date dateTo;
    private Time beginTime;
    private double ticketPrice;
    private int expoHallId;
    private URL picture;
    private String description;

    public Exposition() {
    }

    public Exposition(int id, String title, String theme, Date dateFrom, Date dateTo,
                      Time beginTime, double ticketPrice, int expoHallId, URL picture, String description) {
        this.id = id;
        this.title = title;
        this.theme = theme;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.beginTime = beginTime;
        this.ticketPrice = ticketPrice;
        this.expoHallId = expoHallId;
        this.picture = picture;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getExpoHallId() {
        return expoHallId;
    }

    public void setExpoHallId(int expoHallId) {
        this.expoHallId = expoHallId;
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exposition that = (Exposition) o;

        if (id != that.id) return false;
        if (Double.compare(that.ticketPrice, ticketPrice) != 0) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (theme != null ? !theme.equals(that.theme) : that.theme != null) return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        return dateTo != null ? dateTo.equals(that.dateTo) : that.dateTo == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        temp = Double.doubleToLongBits(ticketPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + expoHallId;
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "title='" + title + '\'' +
                ", theme='" + theme + '\'' +
                ", startDate=" + dateFrom +
                ", endDate=" + dateTo +
                '}';
    }


    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String title;
        private String theme;
        private Date dateFrom;
        private Date dateTo;
        private Time beginTime;
        private double ticketPrice;
        private int expoHallId;
        private URL picture;
        private String description;

        private Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTheme(String theme) {
            this.theme = theme;
            return this;
        }

        public Builder setDateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public Builder setDateTo(Date dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public Builder setBeginTime(Time beginTime) {
            this.beginTime = beginTime;
            return this;
        }

        public Builder setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        public Builder setExpoHallId(int expoHallId) {
            this.expoHallId = expoHallId;
            return this;
        }

        public Builder setPicture(URL picture) {
            this.picture = picture;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Exposition build() {
            return new Exposition(id, title, theme, dateFrom, dateTo,
                    beginTime, ticketPrice, expoHallId, picture, description);
        }

    }
}
