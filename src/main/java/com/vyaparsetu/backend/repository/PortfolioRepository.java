package com.vyaparsetu.backend.repository;

import com.vyaparsetu.backend.entities.Portfolios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolios , Long> {
    Portfolios findUserByUserName(String userName);
}
