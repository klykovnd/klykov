package com.expocalendar.project.entities;

import java.io.Serializable;

public class ExpoHall implements Serializable {
    private int id;
    private String name;
    private String address;

    public ExpoHall() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String city) {
        this.address = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpoHall expoHall = (ExpoHall) o;

        if (id != expoHall.id) return false;
        if (name != null ? !name.equals(expoHall.name) : expoHall.name != null) return false;
        return address != null ? address.equals(expoHall.address) : expoHall.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExpoHall{" +
                "name='" + name + '\'' +
                ", city='" + address + '\'' +
                '}';
    }
}
