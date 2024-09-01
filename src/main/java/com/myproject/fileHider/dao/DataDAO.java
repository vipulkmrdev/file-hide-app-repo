package com.myproject.fileHider.dao;

import com.myproject.fileHider.database.DBConnection;
import com.myproject.fileHider.model.Data;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// To show hidden files, hide and unhide files
public class DataDAO {

    public static List<Data> getAllHiddenFiles(String email) throws SQLException {
        String query = "SELECT * FROM data WHERE email = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        List<Data> files = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String fileName = rs.getString("name");
            String filePath = rs.getString("path");
            files.add(new Data(id, fileName, filePath));
        }
        return files;
    }

    public static int hideFile(Data file) throws SQLException, IOException {
        String query = "INSERT INTO data (name, path, email, bin_data) VALUES (?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, file.getFileName());  // File name
        ps.setString(2, file.getFilePath());  // File path
        ps.setString(3, file.getEmail());     // Logged in user's email
        File f = new File(file.getFilePath());
        FileReader fr = new FileReader(f);
        ps.setCharacterStream(4, fr, f.length());  // Insert file
        int ans = ps.executeUpdate();
        fr.close();
        f.delete();   // We delete the file once transferred in DB. While unhiding we create a new file at the same path
        return ans;
    }

    public static void unHideFile(int id) throws SQLException, IOException {
        String query = "SELECT path, bin_data FROM data WHERE id = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String path = rs.getString("path");
        Clob clob = rs.getClob("bin_data");

        Reader r = clob.getCharacterStream();
        FileWriter fw = new FileWriter(path);  // Characters from reader will be written at the given path
        int i;  // Character come as ASCII value
        while ((i = r.read()) != -1){
            fw.write((char) i);
        }
        fw.close();

        // To delete the file entry from DB
        String deleteQuery = "DELETE FROM data WHERE id = ?";
        ps = connection.prepareStatement(deleteQuery);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("File unhidden");
    }

}
