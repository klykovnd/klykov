package com.expocalendar.project.web.service.interfaces;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;

import java.util.List;
import java.util.Map;

public interface SelectionService {

    List<ExpoHall> getExpoHalls();

    ExpoHall getExpoHall(int id);

    List<Exposition> getAllExpositions();

    List<Exposition> findExpositions(Map<String, String> requestParameters, int limit, int offset);

    int getNumberOfExpositions(Map<String, String> requestParameters);

    List<String> findThemes();

    Exposition getExposition(Integer id);
}
