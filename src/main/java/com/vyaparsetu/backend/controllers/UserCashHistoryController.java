package com.vyaparsetu.backend.controllers;

import com.vyaparsetu.backend.entities.UserCashHistory;
import com.vyaparsetu.backend.service.UserCashHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cash")
public class UserCashHistoryController {

    @Autowired
    private UserCashHistoryService userCashHistoryService;

    @PostMapping("/add-funds")
    public ResponseEntity<?> addFunds(@RequestBody UserCashHistory userCashHistory, @RequestParam Long id){
        boolean saved = userCashHistoryService.addFunds(userCashHistory, id);
        if(saved)return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

    }

    @PostMapping("/withdraw-funds")
    public ResponseEntity<?> withdrawFunds(@RequestBody UserCashHistory userCashHistory , @RequestParam Long id){
        boolean saved = userCashHistoryService.withdrawFunds(userCashHistory, id);
        if(saved)return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }
}
