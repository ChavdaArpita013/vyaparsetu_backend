package com.vyaparsetu.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StockHoldings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stockId;

    private String stockName;

    private int qty;

    private double avgPrice;//average price

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolios portfolio;
}
