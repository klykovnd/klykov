package com.expocalendar.project.entities;

import java.io.Serializable;

public class CreditCard implements Serializable {
    private int id;
    private String number;
    private String CVV;
    private int balance;
    private String holder;
    private String month;
    private String year;


    public CreditCard() {
    }

    public CreditCard(String number, String CVV, String holder, String month, String year) {
        this.number = number;
        this.CVV = CVV;
        this.holder = holder;
        this.month = month;
        this.year = year;
    }

    public CreditCard(int id, String number, String CVV, int balance, String holder, String month, String year) {
        this.id = id;
        this.balance = balance;
        this.number = number;
        this.CVV = CVV;
        this.holder = holder;
        this.month = month;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (id != that.id) return false;
        if (balance != that.balance) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (CVV != null ? !CVV.equals(that.CVV) : that.CVV != null) return false;
        if (holder != null ? !holder.equals(that.holder) : that.holder != null) return false;
        if (month != null ? !month.equals(that.month) : that.month != null) return false;
        return year != null ? year.equals(that.year) : that.year == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + balance;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (CVV != null ? CVV.hashCode() : 0);
        result = 31 * result + (holder != null ? holder.hashCode() : 0);
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", balance=" + balance +
                ", number='" + number + '\'' +
                ", CVV='" + CVV + '\'' +
                ", holder='" + holder + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
