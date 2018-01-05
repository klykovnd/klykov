package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLExpositionDAO implements ExpositionDAO {
    private static MySQLExpositionDAO instance;
    private final IDataSourceManager dataSourceManager;

    private final static Logger LOGGER = Logger.getLogger(MySQLExpositionDAO.class);

    private static final String FIND_EXPOSITION = "SELECT * FROM expositions WHERE exposition_id = ?";

    private static final String INSERT_EXPOSITION = "INSERT INTO expositions" +
            "(exposition_name, date_from, date_to, theme, ticket_price, expohall_id) " + "VALUES(?,?,?,?,?,?)";

    private static final String SELECT_BY_DATE = "SELECT * FROM expositions WHERE " +
            "(date_from BETWEEN '%s' AND '%s' OR date_to BETWEEN '%s' AND " +
            "'%s' OR date_from <= '%s' AND date_to >= '%s')";

    private static final String SELECT_BY_THEME = " AND theme = '%s'";
    private static final String SELECT_BY_HALL = " AND expohall_id = %s";


    private MySQLExpositionDAO() {
        dataSourceManager = MySQLDataSourceManager.getInstance();
    }

    public static MySQLExpositionDAO getInstance() {
        if (instance == null) {
            instance = new MySQLExpositionDAO();
        }
        return instance;
    }


    @Override
    public List<String> findThemes() {
        List<String> themes = new ArrayList<>();
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT DISTINCT theme FROM expositions");
            while (rs.next()) {
                themes.add(rs.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }

        return themes;
    }

    @Override
    public String findMaxDate() {
        String date = null;
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT MAX(date_to) FROM expositions");
            if (rs.next()) {
                date = rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }

        return date;
    }

    public List<Exposition> findExpositions(String query) {
        List<Exposition> expositions = new ArrayList<>();
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                expositions.add(processRow(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }
        return expositions;
    }


    @Override
    public boolean createExposition(Exposition exposition) {
        boolean flag = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EXPOSITION)) {
            prepareForCreation(preparedStatement, exposition);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }

        return flag;
    }

    @Override
    public Exposition findExposition(int expositionId) {
        Exposition exposition = null;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_EXPOSITION)) {
            ps.setInt(1, expositionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exposition = processRow(rs);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }
        return exposition;
    }


    private Exposition processRow(ResultSet rs) throws SQLException {
        Exposition exposition = new Exposition();
        exposition.setId(rs.getInt(1));
        exposition.setName(rs.getString(2));
        exposition.setDateFrom(rs.getDate(3));
        exposition.setDateTo(rs.getDate(4));
        exposition.setTheme(rs.getString(5));
        exposition.setTicketPrice(rs.getInt(6));
        exposition.setExpoHallId(rs.getInt(7));
        return exposition;
    }


    private void prepareForCreation(PreparedStatement ps, Exposition exposition) throws SQLException {
        ps.setString(1, exposition.getName());
        ps.setDate(2, new Date(exposition.getDateFrom().getTime()));
        ps.setDate(3, new Date(exposition.getDateTo().getTime()));
        ps.setString(4, exposition.getTheme());
        ps.setInt(5, exposition.getTicketPrice());
        ps.setInt(6, exposition.getExpoHallId());
    }

    public String parseQuery(Map<String, String> parameters) {
        StringBuilder stringBuilder = new StringBuilder();

        String dateFrom = parameters.get("dateFrom");
        String dateTo = parameters.get("dateTo");

        stringBuilder.append(String.format(SELECT_BY_DATE, dateFrom, dateTo, dateFrom, dateTo, dateFrom, dateTo));

        if (!parameters.get("theme").equalsIgnoreCase("all")) {
            stringBuilder.append(String.format(SELECT_BY_THEME, parameters.get("theme")));
        }
        if (!parameters.get("hallId").equalsIgnoreCase("")) {
            stringBuilder.append(String.format(SELECT_BY_HALL, parameters.get("hallId")));
        }
        stringBuilder.append(" ORDER BY date_to ASC");
        LOGGER.log(Level.INFO, stringBuilder.toString());

        return stringBuilder.toString();
    }
}
