package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;

public class MySQLAccountDAO implements AccountDAO {
    private final static Logger LOGGER = Logger.getLogger(MySQLAccountDAO.class);

    private static final String INSERT_ACCOUNT = "INSERT INTO accounts" +
            "(first_name,last_name,login,password,email) " + "VALUES(?,?,?,?,?)";
    private static final String FIND_ACCOUNT = "SELECT * FROM accounts WHERE login = ? AND password = ?";
    private static final String FIND_LOGIN = "SELECT * FROM accounts WHERE login = ? AND email = ? ";
    private static final String SAVE_ORDER = "INSERT INTO orders (account_id,expo_id) VALUES (?,?)";
    private static final String WITHDRAW = "UPDATE accounts SET balance = ? WHERE account_id = ?";

    private static MySQLAccountDAO instance;

    private IDataSourceManager dataSourceManager;

    private MySQLAccountDAO() {
        dataSourceManager = MySQLDataSourceManager.getInstance();
    }

    public static MySQLAccountDAO getInstance() {
        if (instance == null) {
            instance = new MySQLAccountDAO();
        }
        return instance;
    }


    @Override
    public Account findAccount(String login, String password) {
        Account account = null;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ACCOUNT)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = processRow(rs);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }
        return account;
    }

    @Override
    public void createAccount(Account account) {
        boolean flag = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);) {
            prepareForCreation(preparedStatement, account);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }
    }

    @Override
    public boolean isExist(String login, String email) {
        boolean exist = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            exist = rs.next();

        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }
        return exist;
    }

    @Override
    public void saveOrder(Account account, int expoId, int remainder) {
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement prepInsert = connection.prepareStatement(SAVE_ORDER);
             PreparedStatement prepWithdraw = connection.prepareStatement(WITHDRAW)) {
            connection.setAutoCommit(false);

            prepInsert.setInt(1, account.getId());
            prepInsert.setInt(2, expoId);
            prepInsert.executeUpdate();

            prepWithdraw.setInt(1, remainder);
            prepWithdraw.setInt(2, account.getId());
            prepWithdraw.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }
    }

    private Account processRow(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt(1));
        account.setFirstName(rs.getString(2));
        account.setLastName(rs.getString(3));
        account.setLogin(rs.getString(4));
        account.setPassword(rs.getString(5));
        account.setRole(rs.getString(6));
        account.setEmail(rs.getString(7));
        account.setBalance(rs.getInt(8));

        return account;
    }


    private void prepareForCreation(PreparedStatement ps, Account account) throws SQLException {
        ps.setString(1, account.getFirstName());
        ps.setString(2, account.getLastName());
        ps.setString(3, account.getLogin());
        ps.setString(4, account.getPassword());
        ps.setString(5, account.getEmail());
    }
}
