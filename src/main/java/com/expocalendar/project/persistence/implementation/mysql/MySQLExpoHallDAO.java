package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLExpoHallDAO implements ExpoHallDAO {
    private static MySQLExpoHallDAO instance;
    private final IDataSourceManager dataSourceManager;

    private final static Logger LOGGER = Logger.getLogger(MySQLExpoHallDAO.class);

    private static final String SQL_ALL_EXPO_HALLS = "SELECT * FROM expohalls";

    private MySQLExpoHallDAO() {
        dataSourceManager = MySQLDataSourceManager.getInstance();
    }

    public static MySQLExpoHallDAO getInstance() {
        if (instance == null) {
            instance = new MySQLExpoHallDAO();
        }
        return instance;
    }

    public List<ExpoHall> findAll() {
        List<ExpoHall> expoHalls = new ArrayList<>();
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_ALL_EXPO_HALLS);
            while (rs.next()) {
                expoHalls.add(processRow(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }

        return expoHalls;
    }


    public ExpoHall findById(int expoHallId) {
        ExpoHall expoHall = null;
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM expohalls WHERE expohall_id =" + expoHallId);
            while (rs.next()) {
                expoHall = processRow(rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }
        return expoHall;
    }


    @Override
    public List<String> findAllCities() {
        List<String> cities = new ArrayList<>();
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT DISTINCT city FROM expohalls");
            while (rs.next()) {
                cities.add(rs.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }

        return cities;
    }

    public List<ExpoHall> findByCity(String city) {
        List<ExpoHall> expoHalls = new ArrayList<>();
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_ALL_EXPO_HALLS + " WHERE city = " + "'" + city + "'");
            while (rs.next()) {
                expoHalls.add(processRow(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }

        return expoHalls;
    }

    private ExpoHall processRow(ResultSet rs) throws SQLException {
        ExpoHall expoHall = new ExpoHall();
        expoHall.setId(rs.getInt(1));
        expoHall.setName(rs.getString(2));
        expoHall.setCity(rs.getString(3));
        return expoHall;
    }
}
