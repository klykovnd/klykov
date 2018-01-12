package com.expocalendar.project.persistence.abstraction.interfaces;

import com.expocalendar.project.entities.ExpoHall;

import java.util.List;

public interface ExpoHallDAO {

    List<ExpoHall> findAll();

    ExpoHall findExpoHall(int id);
}
