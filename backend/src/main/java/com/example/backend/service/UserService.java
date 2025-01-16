package com.example.backend.service;

import java.util.List;
import java.util.UUID;

import com.example.backend.models.CreateUserRequest;
import com.example.backend.models.UserResponse;;

public interface UserService {

    // Получение всех пользователей
    List<UserResponse> findAll();

    // Получение пользователя по ID
    UserResponse findById(UUID userId);

    // Получение пользователя по Email
    UserResponse findUserByUserEmail(String userEmail);

    // Добавление нового пользователя
    UserResponse create(CreateUserRequest request);

    // Изменение данных существующего пользователя
    UserResponse update(UUID userId, CreateUserRequest request);

    // Удаление пользователя
    void delete(UUID userId);
}
