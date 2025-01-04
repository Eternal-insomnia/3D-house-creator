package com.example.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.User;

// Всё для работы с БД
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // Найти пользователя по email
    // Optional<User> findByUserEmail(String userEmail);
}
