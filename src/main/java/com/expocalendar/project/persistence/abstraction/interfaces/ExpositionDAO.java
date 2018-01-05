package com.expocalendar.project.persistence.abstraction.interfaces;

import com.expocalendar.project.entities.Exposition;

import java.util.List;
import java.util.Map;

public interface ExpositionDAO {

    List<Exposition> findExpositions(String query);

    String parseQuery(Map<String, String> parameters);

    String findMaxDate();

    List<String> findThemes();

    boolean createExposition(Exposition exposition);

    Exposition findExposition(int expositionId);

}
