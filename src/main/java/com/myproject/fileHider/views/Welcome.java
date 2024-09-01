package com.myproject.fileHider.views;

import com.myproject.fileHider.dao.UserDAO;
import com.myproject.fileHider.model.User;
import com.myproject.fileHider.service.GenerateOTPService;
import com.myproject.fileHider.service.SendOTPService;
import com.myproject.fileHider.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the File Hider application");
        System.out.println("Press 1 to Log In");
        System.out.println("Press 2 to Sign Up");
        System.out.println("Press 3 to Exit");
        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (choice){
            case 1 -> logIn();
            case 2 -> signUp();
            case 3 -> System.exit(0);
        }
    }

    private void logIn() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        try {
            if (UserDAO.isExists(email)){
                String genOTP = GenerateOTPService.getOTP();
                SendOTPService.sendOTP(email, genOTP);
                System.out.print("Enter the OTP: ");
                String inputOTP = sc.nextLine();
                if (inputOTP.equals(genOTP)){
                    new UserView(email).homeScreen();
                } else {
                    System.out.println("Wrong OTP!");
                }
            } else {
                System.out.println("User not found! Sign Up.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();

        String genOTP = GenerateOTPService.getOTP();
        SendOTPService.sendOTP(email, genOTP);
        System.out.print("Enter the OTP: ");
        String inputOTP = sc.nextLine();

        if (inputOTP.equals(genOTP)){
            Integer response = UserService.saveUser(new User(name, email));
            switch (response){
                case 0 -> System.out.println("User already exists!");
                case 1 -> System.out.println("User successfully registered!");
                case 2 -> System.out.println("Failed to register user");
                case -1 -> System.out.println("An error occurred while saving the user");
                default -> System.out.println("Unexpected response code: " + response);
            }
        } else {
            System.out.println("Wrong OTP!");
        }
    }

}

