package com.myproject.fileHider.dao;

import com.myproject.fileHider.database.DBConnection;
import com.myproject.fileHider.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Check if a user with the given email already exists
    public static boolean isExists(String email) throws SQLException {
        String query = "SELECT email FROM users WHERE email = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, email);  // Set input email in the query
        ResultSet rs = ps.executeQuery();  // The query with input email is executed and output results (if any) are stored in rs
        return rs.next();  // If a result is found, user exists
    }

    // Save a new user
    public static int saveUser(User user) throws SQLException {
        String query = "INSERT INTO users (id, name, email) VALUES (default, ?, ?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        return ps.executeUpdate(); //executeUpdate returns no. of rows affected, if 0 then insertion not successful
    }

}
