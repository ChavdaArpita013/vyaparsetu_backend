package com.vyaparsetu.backend.repository;

import com.vyaparsetu.backend.entities.Portfolios;
import com.vyaparsetu.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolios , Long> {
    Portfolios findByUser_UserName(String userName);
    Portfolios findByUser(User user);
}
