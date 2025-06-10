package com.vyaparsetu.backend.controllers;

import com.vyaparsetu.backend.entities.StockHoldings;
import com.vyaparsetu.backend.service.StockHoldingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class StockHoldingsController {

    private StockHoldingsService stockHoldingsService;

    @PostMapping("/buy-stock")
    public ResponseEntity<?> addStock(@RequestBody StockHoldings holdings){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            boolean b = stockHoldingsService.addStockHoldings(holdings, name);
            if(b){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/sell-stock")
    public ResponseEntity<?> sellStock(@RequestBody StockHoldings holdings){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        boolean saved = stockHoldingsService.sellStockHoldings(holdings, name);
        if(saved){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
