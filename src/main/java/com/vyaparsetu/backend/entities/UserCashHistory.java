package com.vyaparsetu.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class UserCashHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;
    private Double amount;
    private String type; //cash added or withdrawn
    private String description;
    @ManyToOne
    private User user;
}
