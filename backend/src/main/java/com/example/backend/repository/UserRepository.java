package com.example.backend.repository;

import com.example.backend.entity.User;
import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// Всё для работы с БД
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    // Найти пользователя по email
    @Query("SELECT u FROM User u WHERE u.userEmail = ?1")
    Optional<User> findUserByUserEmail(String userEmail);
}
