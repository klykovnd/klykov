package com.expocalendar.project.entities;

import java.io.Serializable;

public class CreditCard implements Serializable {
    private int id;
    private String number;
    private int CVV;
    private double balance;
    private String holder;
    private int month;
    private int year;


    public CreditCard() {
    }

    public CreditCard(int id, String number, int CVV, double balance, String holder, int month, int year) {
        this.id = id;
        this.number = number;
        this.CVV = CVV;
        this.balance = balance;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (id != that.id) return false;
        if (CVV != that.CVV) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (month != that.month) return false;
        if (year != that.year) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        return holder != null ? holder.equals(that.holder) : that.holder == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + CVV;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (holder != null ? holder.hashCode() : 0);
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", CVV=" + CVV +
                ", balance=" + balance +
                ", holder='" + holder + '\'' +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String number;
        private int CVV;
        private double balance;
        private String holder;
        private int month;
        private int year;

        private Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setCVV(int CVV) {
            this.CVV = CVV;
            return this;
        }

        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder setHolder(String holder) {
            this.holder = holder;
            return this;
        }

        public Builder setMonth(int month) {
            this.month = month;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public CreditCard build() {
            return new CreditCard(id, number, CVV, balance, holder, month, year);
        }

    }
}
