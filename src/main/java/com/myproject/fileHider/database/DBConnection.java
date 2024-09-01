package com.myproject.fileHider.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/*
 * Utility class for managing database connections using HikariCP.
 * This class provides static methods to obtain and close database connections.
 */
public class DBConnection {

    // Static data source instance, initialized once and shared across the application
    private static HikariDataSource dataSource;

    // Static block to initialize HikariDataSource when class is first loaded
    static {
        try {
            // Configuration of HikariCP
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/fileHider?useSSL=true");
            config.setUsername("root");
            config.setPassword("root");
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            config.setMaximumPoolSize(10); // The pool size can be adjusted as needed
            config.setConnectionTestQuery("SELECT 1"); // Validate connections to ensure they are still valid

            // Initialize HikariDataSource with the configuration
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Private constructor to prevent instantiation
    private DBConnection() {
        // Part of singleton-pattern, ensuring only one instance of this class is created
    }

    // Static method to get a connection from the pool
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Static method to close the data source thus releasing all the resources
    public static void closeConnection() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

}
