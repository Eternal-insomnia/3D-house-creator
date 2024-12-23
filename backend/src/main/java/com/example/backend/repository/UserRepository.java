package com.example.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Найти пользователя по email
    Optional<User> findByUserEmail(String userEmail);
}
