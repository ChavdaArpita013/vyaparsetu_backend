package com.vyaparsetu.backend.repository;

import com.vyaparsetu.backend.entities.UserCashHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCashHistoryRepository extends JpaRepository<UserCashHistory , Long> {
}
