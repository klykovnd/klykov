package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLExpoHallDAO implements ExpoHallDAO {
    private static MySQLExpoHallDAO instance;
    private final IDataSourceManager dataSourceManager;
    private final static Logger LOGGER = Logger.getLogger(MySQLExpoHallDAO.class);

    private static final int ID = 1;
    private static final int NAME = 2;
    private static final int ADDRESS = 3;


    private static final String FIND_ALL_EXPOHALLS = "SELECT * FROM expohalls";
    private static final String FIND_EXPOHAll = "SELECT * FROM expohalls WHERE expohall_id = ?";


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
            ResultSet rs = statement.executeQuery(FIND_ALL_EXPOHALLS);
            while (rs.next()) {
                expoHalls.add(processRow(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }

        return expoHalls;
    }

    @Override
    public ExpoHall findExpoHall(int id) {
        ExpoHall expoHall = null;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_EXPOHAll)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                expoHall = processRow(rs);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }
        return expoHall;
    }

    private ExpoHall processRow(ResultSet rs) throws SQLException {
        return new ExpoHall(rs.getInt(ID), rs.getString(NAME), rs.getString(ADDRESS));
    }
}
