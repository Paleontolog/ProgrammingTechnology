package tasks.bank_application.database;

import org.h2.util.ScriptReader;
import tasks.bank_application.entities.Account;
import tasks.bank_application.constants.Currencies;
import tasks.bank_application.entities.Operation;
import tasks.bank_application.entities.User;
import tasks.bank_application.utils.PropertyLoader;

import java.beans.PersistenceDelegate;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseAccessorJDBC implements DatabaseAccessor {
    private Connection connection;

    public DatabaseAccessorJDBC(Connection connection) throws FileNotFoundException {
        this.connection = connection;
        initializeDatabase();
    }

    private void initializeDatabase() throws FileNotFoundException {
        Properties properties = PropertyLoader.loadProperties("src/main/resources/config.properties");
        ScriptReader scriptReader =
                new ScriptReader(new BufferedReader(new FileReader(properties.getProperty("db.init_script"))));
        try (Statement statement = connection.createStatement()) {
            String script;
            while ((script = scriptReader.readStatement()) != null) {
                statement.execute(script);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public int createUser(User user) {
        String query = "INSERT INTO BANK.USER VALUES (null, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getPhone());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int createAccount(Account account) {
        String query = "INSERT INTO BANK.ACCOUNT VALUES (null, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, account.getClientId());
            statement.setBigDecimal(2, account.getAmount());
            statement.setString(3, account.getAccCode().toString());
            statement.setInt(4, account.getIsMain());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private User mapToUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("ID"),
                resultSet.getString("LOGIN"),
                resultSet.getString("PASSWORD"),
                resultSet.getString("ADDRESS"),
                resultSet.getString("PHONE"));
    }

    @Override
    public User getUser(String login, String phone) {
        String query = "SELECT * FROM BANK.USER WHERE LOGIN=? OR PHONE=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, phone);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                user = mapToUser(resultSet);
            }
            resultSet.close();
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int setMoney(Long accountID, BigDecimal money) {
        String query = "UPDATE BANK.ACCOUNT " +
                "SET AMOUNT = ? " +
                "WHERE ID = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBigDecimal(1, money);
            statement.setLong(2, accountID);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    private Account mapToAccount(ResultSet resultSet) throws SQLException {
        return new Account(resultSet.getLong("ID"),
                resultSet.getLong("CLIENT_ID"),
                resultSet.getBigDecimal("AMOUNT"),
                Currencies.valueOf(resultSet.getString("ACC_CODE")),
                resultSet.getInt("IS_MAIN"));
    }


    @Override
    public Account getAccount(Long accountId) {
        String query = "SELECT * FROM BANK.ACCOUNT WHERE ID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            Account account = null;
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                account = mapToAccount(resultSet);
            }
            resultSet.close();
            return account;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Account> getUserAccounts(Long userId) {
        String query = "SELECT * FROM BANK.ACCOUNT WHERE CLIENT_ID=?";
        List<Account> accountList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountList.add(mapToAccount(resultSet));
            }
            resultSet.close();
            return accountList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int logOperation(Operation operation) {
        String query = "INSERT INTO BANK.OPERATION VALUES (null, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, operation.getOperationDate());
            statement.setLong(2, operation.getFromId());
            statement.setLong(3, operation.getToId());
            statement.setBigDecimal(4, operation.getTransAmount());
            statement.setBigDecimal(5, operation.getBeforeTransfer());
            statement.setBigDecimal(6, operation.getAfterTransfer());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private Operation mapToOperation(ResultSet resultSet) throws SQLException {
        return new Operation(resultSet.getLong("ID"),
                resultSet.getDate("OPERATION_DATE"),
                resultSet.getLong("FROM_ID"),
                resultSet.getLong("TO_ID"),
                resultSet.getBigDecimal("TRANS_AMOUNT"),
                resultSet.getBigDecimal("BEFORE_TRANSFER"),
                resultSet.getBigDecimal("AFTER_TRANSFER"));
    }

    @Override
    public List<Operation> getUserOperation(Long accountID) {
        String query = "SELECT * FROM BANK.OPERATION WHERE FROM_ID=? OR TO_ID=?";
        List<Operation> accountList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, accountID);
            statement.setLong(2, accountID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountList.add(mapToOperation(resultSet));
            }
            resultSet.close();
            return accountList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public void destroyConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DatabaseAccessor accessor = new DatabaseAccessorJDBC(DatabaseConnector.getConnection());
    }


}
