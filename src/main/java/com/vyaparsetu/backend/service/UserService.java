package com.vyaparsetu.backend.service;

import com.vyaparsetu.backend.entities.BankDetails;
import com.vyaparsetu.backend.entities.User;
import com.vyaparsetu.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    public User findUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public String generateUserName(){
        return "ABC020";
    }

    public boolean createDeMateAccount(User user){
        try {
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setMobileNo(user.getMobileNo());
            newUser.setBankAccounts(user.getBankAccounts());
            User save = userRepository.save(newUser);

            if(save != null){
               emailService.sendMail(user.getEmail() , user.getUserName());
               return true;
            }


        } catch (Exception e) {
            log.info("Error creating demate account " , e);
        }
        return false;
    }

    public boolean loginUser(String userName , String userId , String password){
        User user = userRepository.findByUserName(userName);
        return Objects.equals(user.getUserId(), userId) && Objects.equals(user.getPassword(), password);
    }
}
