package com.vyaparsetu.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to , String userName , String userId , String password){
        try {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject("VyaparSetu Account Credentials");
            StringBuilder message = new StringBuilder();
            message.append("Dear ").append(userName).append(",\n\n");
            message.append("Welcome to VyaparSetu!\n\n");
            message.append("Your demat account has been successfully created. Below are your login credentials:\n\n");
            message.append("User ID: ").append(userId).append("\n");
            message.append("Password: ").append(password).append("\n\n");
            message.append("For your security, we strongly recommend that you change your password upon first login.\n");
            message.append("Please keep these credentials confidential and do not share them with anyone.\n\n");
            message.append("If you have any questions or require assistance, feel free to reach out to our support team.\n\n");
            message.append("Thank you for choosing VyaparSetu.\n\n");
            message.append("Warm regards,\n");
            message.append("Team VyaparSetu");

            simpleMailMessage.setText(message.toString());


        } catch (Exception e) {
            log.info("Error sending mail for new user credentials (new demate account)" , e);
        }
    }
}
