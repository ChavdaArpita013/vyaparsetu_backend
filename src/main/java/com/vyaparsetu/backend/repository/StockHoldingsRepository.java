package com.vyaparsetu.backend.repository;

import com.vyaparsetu.backend.entities.Portfolios;
import com.vyaparsetu.backend.entities.StockHoldings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockHoldingsRepository extends JpaRepository<StockHoldings , Long> {

    StockHoldings findByStockIdAndPortfolio(Long stockId , Portfolios portfolios);
}
