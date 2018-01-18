package com.expocalendar.project.web.service.interfaces;


import java.net.MalformedURLException;
import java.util.Map;

public interface AdminService {

    void createExposition(Map<String, String> requestParameters) throws MalformedURLException;

    void updateExposition(Map<String, String> requestParameters) throws MalformedURLException;

    void deleteExposition(int expositionId);

    void createExpoHall(Map<String, String> requestParameters);

    void updateExpoHall(Map<String, String> requestParameters);

    void deleteExpoHall(int expoHallId);
}
