package com.vyaparsetu.backend.repository;

import com.vyaparsetu.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
