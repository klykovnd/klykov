package com.expocalendar.project.persistence.abstraction.interfaces;

import com.expocalendar.project.entities.Exposition;

import java.util.List;
import java.util.Map;

public interface ExpositionDAO {

    List<Exposition> findAll();

    List<Exposition> findExpositions(String query);

    int countExpositions(String query);

    Exposition findExposition(int expositionId);

    List<String> findThemes();

    void createExposition(Exposition exposition);

    void updateExposition(Exposition exposition);

    void deleteExposition(int expositionId);

    String parseQuery(Map<String, String> parameters, int limit, int offset);

    String countQuery(Map<String, String> parameters);

}
