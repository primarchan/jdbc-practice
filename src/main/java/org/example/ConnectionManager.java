package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String DB_DRIVER = "org.h2.Driver";  // 단축키 : command + option + C
    private static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    public static final String USER_NAME = "sa";
    public static final String USER_PASSWORD = "";
    public static final int MAX_POOL_SIZE = 40;
    private static final DataSource ds;

    static {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername(USER_NAME);
        hikariDataSource.setPassword(USER_PASSWORD);
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

        ds = hikariDataSource;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }

}
