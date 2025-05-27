package com.vyaparsetu.backend.controllers;

import com.vyaparsetu.backend.config.SpringSecurity;
import com.vyaparsetu.backend.entities.User;
import com.vyaparsetu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class publicController {
    @Autowired
    private UserService userService;

    @GetMapping("/ok")
    public String healthCheck(){
        return "Welcome to VyaparSetu Backend";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(String userName , String password){
        User user = userService.findUserByUserName(userName);
        if(user != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
