package com.vyaparsetu.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long stockId;

    private String stockName;

    private int qty;

    private String transactionType; //buy or sell

    private double price; //if buy(buyprice) else (sellprice)

    private LocalDateTime time;

    @ManyToOne
    private User user;

}
