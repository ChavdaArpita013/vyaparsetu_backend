package com.vyaparsetu.backend.controllers;

import com.vyaparsetu.backend.config.SpringSecurity;
import com.vyaparsetu.backend.entities.User;
import com.vyaparsetu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createDeMate(User user){
        boolean deMateAccount = userService.createDeMateAccount(user);
        //send mail with username password tpin
        if(deMateAccount)return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(String userId , String password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean login = userService.loginUser(userName, userId, password);
        if(login){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
