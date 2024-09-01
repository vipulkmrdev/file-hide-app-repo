package com.myproject.fileHider.views;

import com.myproject.fileHider.dao.DataDAO;
import com.myproject.fileHider.model.Data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {

    private String email;   // Email of the logged in user

    UserView(String email){
        this.email = email;
    }

    public void homeScreen(){
        do {
            System.out.println("Welcome " + this.email);
            System.out.println("Press 1 to show hidden files");
            System.out.println("Press 2 to hide new file");
            System.out.println("Press 3 to unhide a file");
            System.out.println("Press 0 to exit");

            Scanner sc = new Scanner(System.in);
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1 -> {
                    try {
                        List<Data> files = DataDAO.getAllHiddenFiles(this.email);
                        if (files.isEmpty()){
                            System.out.println("No hidden files!");
                        } else {
                            System.out.println("ID - File Name");
                            for (Data file : files){
                                System.out.println(file.getId() + " - " + file.getFileName());
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 2 -> {
                    System.out.print("Enter the file path: ");
                    String path = sc.nextLine();
                    File f = new File(path);
                    Data file = new Data(0, f.getName(), path, this.email);
                    try {
                        DataDAO.hideFile(file);
                        System.out.println("File hidden successfully!");
                        System.out.println();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                case 3 -> {
                    try {
                        List<Data> files = DataDAO.getAllHiddenFiles(this.email);
                        System.out.println("ID - File Name");
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getFileName());
                        }
                        System.out.println("Enter the id of file to unhide");
                        int id = Integer.parseInt(sc.nextLine());
                        boolean isValidId = false;
                        for (Data file : files){
                            if (file.getId() == id){
                                isValidId = true;
                                break;
                            }
                        }
                        if (isValidId){
                            DataDAO.unHideFile(id);
                        } else {
                            System.out.println("Wrong id selected");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                case 0 -> {
                    System.exit(0);
                }
            }
        } while (true);

    }



}
