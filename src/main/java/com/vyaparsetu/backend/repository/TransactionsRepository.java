package com.vyaparsetu.backend.repository;

import com.vyaparsetu.backend.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions , Long> {
}
