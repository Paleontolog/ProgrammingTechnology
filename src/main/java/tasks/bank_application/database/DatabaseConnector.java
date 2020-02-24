package tasks.bank_application.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.util.ScriptReader;
import tasks.bank_application.utils.PropertyLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
//    private static BasicDataSource ds = new BasicDataSource();
//
//    static {
//        Properties property = PropertyLoader.loadProperties("src/main/resources/config.properties");
//        ds.setUrl(property.getProperty("db.url"));
//        ds.setUsername(property.getProperty("db.login"));
//        ds.setPassword(property.getProperty("db.password"));
//    }
//
//    public static Connection getConnection() throws SQLException {
//        return ds.getConnection();
//    }

//    private DatabaseConnector() { }

    public static Connection getConnection() throws SQLException {
        Properties property = PropertyLoader.loadProperties("src/main/resources/config.properties");
        return  DriverManager.getConnection(property.getProperty("db.url"),
                                            property.getProperty("db.login"),
                                            property.getProperty("db.password"));
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConnector.getConnection();
    }
}
