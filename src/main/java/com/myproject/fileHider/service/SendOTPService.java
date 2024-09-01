package com.myproject.fileHider.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendOTPService {
    public static void sendOTP(String email, String genOTP) {
        // User's email
        String sendTo = email;

        // System emailID sendTo send the OTP
        String sendFrom = "work.vipulkumar@gmail.com";

        // To send email through Gmail SMTP
        String host = "smtp.gmail.com";

        // To get system application.properties
        Properties properties = System.getProperties();

        // To setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // To get the Session object and pass username & password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendFrom, "eoeueyfgshscciwr");
            }
        });

        // To debug SMTP issues, if any arises
            // session.setDebug(true);

        try {
            // To create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // To set From: header field of the header
            message.setFrom(new InternetAddress(sendFrom));

            // To set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));

            // To set Subject: header field
            message.setSubject("File Hider OTP access");

            // To set the actual message
            message.setText("Your One Time Password for File Hider app is: " + genOTP);

            // Send message
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
