package com.myproject.fileHider.service;

import com.myproject.fileHider.dao.UserDAO;
import com.myproject.fileHider.model.User;

import java.sql.SQLException;

public class UserService {
    public static Integer saveUser(User user){
        try {
            if(UserDAO.isExists(user.getEmail())){
                return 0; // User already exists
            } else {
               int result = UserDAO.saveUser(user);
               return result > 0 ? 1 : 2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
