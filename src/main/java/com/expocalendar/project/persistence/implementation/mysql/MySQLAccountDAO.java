package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.CreditCard;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;

public class MySQLAccountDAO implements AccountDAO {
    private static MySQLAccountDAO instance;
    private IDataSourceManager dataSourceManager;
    private final static Logger LOGGER = Logger.getLogger(MySQLAccountDAO.class);


    private static final int ID = 1;
    private static final int FIRST_NAME = 2;
    private static final int LAST_NAME = 3;
    private static final int LOGIN = 4;
    private static final int PASSWORD = 5;
    private static final int ROLE = 6;
    private static final int EMAIL = 7;


    private static final String INSERT_ACCOUNT = "INSERT INTO accounts" +
            "(first_name,last_name,login,password,email) " + "VALUES(?,?,?,?,?)";
    private static final String INSERT_CARD = "INSERT INTO cards" +
            "(number,cvv,holder,month,year) " + "VALUES(?,?,?,?,?)";
    private static final String FIND_ACCOUNT = "SELECT * FROM accounts WHERE login = ? AND password = ?";
    private static final String FIND_LOGIN = "SELECT * FROM accounts WHERE login = ? AND email = ? ";
    private static final String SAVE_ORDER = "INSERT INTO orders (account_id,expo_id) VALUES (?,?)";
    private static final String WITHDRAW = "UPDATE cards SET balance = ? WHERE card_id = ?";
    private static final String FIND_CARD = "SELECT * FROM cards WHERE card_id = ?";


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
    public void createAccount(Account account, CreditCard creditCard) {
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement prepareAccount = connection.prepareStatement(INSERT_ACCOUNT);
             PreparedStatement prepareCard = connection.prepareStatement(INSERT_CARD)) {
            connection.setAutoCommit(false);

            prepareAccount(prepareAccount, account);
            prepareAccount.executeUpdate();

            prepareCard(prepareCard, creditCard);
            prepareCard.executeUpdate();

            connection.commit();
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
    public void saveOrder(Account account, int expositionId, double remainder) {
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement prepInsert = connection.prepareStatement(SAVE_ORDER);
             PreparedStatement prepWithdraw = connection.prepareStatement(WITHDRAW)) {
            connection.setAutoCommit(false);

            prepInsert.setInt(1, account.getId());
            prepInsert.setInt(2, expositionId);
            prepInsert.executeUpdate();

            prepWithdraw.setDouble(1, remainder);
            prepWithdraw.setInt(2, account.getId());
            prepWithdraw.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.DEBUG, "SQLException", e);
        }
    }

    private Account processRow(ResultSet rs) throws SQLException {
        return Account.newBuilder().setId(rs.getInt(ID)).
                setFirstName(rs.getString(FIRST_NAME)).
                setLastName(rs.getString(LAST_NAME)).
                setLogin(rs.getString(LOGIN)).
                setPassword(rs.getString(PASSWORD)).
                setRole(rs.getString(ROLE)).
                setEmail(rs.getString(EMAIL)).build();
    }


    private void prepareAccount(PreparedStatement ps, Account account) throws SQLException {
        ps.setString(1, account.getFirstName());
        ps.setString(2, account.getLastName());
        ps.setString(3, account.getLogin());
        ps.setString(4, account.getPassword());
        ps.setString(5, account.getEmail());
    }

    private void prepareCard(PreparedStatement ps, CreditCard creditCard) throws SQLException {
        ps.setString(1, creditCard.getNumber());
        ps.setInt(2, creditCard.getCVV());
        ps.setString(3, creditCard.getHolder());
        ps.setInt(4, creditCard.getMonth());
        ps.setInt(5, creditCard.getYear());
    }
}
