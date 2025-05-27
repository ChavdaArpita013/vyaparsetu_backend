package com.vyaparsetu.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserCashHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;
    private Double amount;
    private String type; //cash added r withdrawn
    @ManyToOne
    private User user;
}
