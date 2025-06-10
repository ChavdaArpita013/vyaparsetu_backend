package com.vyaparsetu.backend.repository;

import com.vyaparsetu.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    @Query("SELECT u.userId FROM User u")
    List<String> findAllUserIds();
}
