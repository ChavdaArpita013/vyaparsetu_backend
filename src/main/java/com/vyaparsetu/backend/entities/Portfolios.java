package com.vyaparsetu.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Portfolios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "portfolio" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<StockHoldings> holdings = new ArrayList<>();
}
