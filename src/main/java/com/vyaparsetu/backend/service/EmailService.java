package com.vyaparsetu.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    public String getUserId(){
        return "ASC020";
    }

    public String getPassword(){
        return "dshguydsda";
    }

    public Integer getTpin(){
        return 398768;
    }

    public void sendMail(String to , String userName){
        try {
            String userId = getUserId();
            String password = getPassword();
            Integer tpin = getTpin();
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject("VyaparSetu Account Credentials");
            simpleMailMessage.setText("Hello" + userName + ",");
            simpleMailMessage.setText("Here is You Vyaparsetu userId" + userId + "and password" + password);
            simpleMailMessage.setText("Also here is you tpin which will be needed to sell any stock " + tpin );
            simpleMailMessage.setText("we request you not to share this credentials with anyone ");
            simpleMailMessage.setText("Regrads" + "team vyaparsetu");

        } catch (Exception e) {
            log.info("Error sending mail for new user credentials (new demate account)" , e);
        }
    }
}
