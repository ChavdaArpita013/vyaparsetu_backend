package com.vyaparsetu.backend.repository;

import com.vyaparsetu.backend.entities.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailRepository extends JpaRepository<BankDetails , Long> {
}
