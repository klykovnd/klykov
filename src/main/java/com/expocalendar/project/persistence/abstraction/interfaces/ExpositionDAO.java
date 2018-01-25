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

    boolean createExposition(Exposition exposition);

    boolean updateExposition(Exposition exposition);

    boolean deleteExposition(int expositionId);

}
