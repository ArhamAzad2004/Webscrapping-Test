package com.example;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    // Method to send an email with the provided email body
    public static void sendEmail(String emailBody) {
        // Email account credentials
        String username = "20220001121@students.cud.ac.ae";
        String password = "xxxxxx";
        // Sender and recipient email addresses
        String fromEmail = "20220001121@students.cud.ac.ae";
        String toEmail = "20220001121@students.cud.ac.ae";

        // Set up properties for mail server configuration
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");

        // Create a mail session with authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a MimeMessage for the email
        MimeMessage msg = new MimeMessage(session);
        try {
            // Set the sender and recipient addresses
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            // Set the subject of the email
            msg.setSubject("Amazon Product Details");
            // Set the content (email body) of the email
            msg.setText(emailBody);
            
            // Send the email using the Transport class
            Transport.send(msg);
            // Print a message indicating that the email was sent successfully
            System.out.println("Sent email with product details");
        } catch (MessagingException e) {
            // Print any errors that occur during the email sending process
            e.printStackTrace();
        }
    }
}
