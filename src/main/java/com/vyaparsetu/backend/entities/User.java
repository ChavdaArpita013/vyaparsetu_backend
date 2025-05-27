package com.vyaparsetu.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String userId;

    private String password;

    private String email;

    private Long mobileNo;

    private Long tpin;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<BankDetails> bankAccounts = new ArrayList<>();

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Transactions> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<UserCashHistory> userCashHistories = new ArrayList<>();

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
    private Portfolios portfolio;


}
