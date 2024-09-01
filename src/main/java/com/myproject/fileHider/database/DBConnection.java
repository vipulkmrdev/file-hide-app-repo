package com.myproject.fileHider.database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection connection;

    public static Connection getConnection(){
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creating the connection
            String url = "jdbc:mysql://localhost:3306/fileHider?useSSL=true";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) { // For Class.forName
            e.printStackTrace();
        } catch (SQLException e) { // For DriverManager.getConnection
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
