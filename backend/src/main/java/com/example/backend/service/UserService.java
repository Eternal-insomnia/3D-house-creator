package com.example.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.backend.entity.User;

/**
 * Service interface for User entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface UserService {

    /**
     * Saves User entity.
     * @param user the user to save
     * @return the saved user
     */
    User saveUser(User user);

    /**
     * Fetches the list of all User entities.
     * @return a list of users
     */
    List<User> fetchUserList();

    /**
     * Fetches the user entity on its UUID
     * @param userId the UUID of user to fetch
     * @return the fetched user entity
     */
    Optional<User> fetchUserById(UUID userId);

    // // Получение пользователя по Email
    // UserResponse findUserByUserEmail(String userEmail);

    // // Добавление нового пользователя
    // UserResponse create(CreateUserRequest request);

    /**
     * Updates an existing user entity.
     * @param user the user with updated information
     * @param userId the ID of the user to update
     * @return the updated user
     */
    User updateUser(User user, UUID userId);

    /**
     * Deletes a User entity by its ID.
     * @param userId the ID fo the user to delete
     */
    void deleteUserById(UUID userId);
}
