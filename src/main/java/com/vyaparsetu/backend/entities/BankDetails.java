package com.vyaparsetu.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankAccNo;

    private String IFSCCode;

    private String bankName;

    private String bankHoldersName;

    private boolean isPrimary;

    @ManyToOne
    private User user;
}
