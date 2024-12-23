package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.entity.LoginRequest;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserRepository userRepository;

    // Получение всех пользователей
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Получение пользователя по ID
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Добавление нового пользователя
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Эндпоинт для проверки пользователя по email и паролю
     *
     * @param loginRequest - объект с полями userEmail и userPassword
     * @return подтверждение успешного входа или ошибка
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.findUserByUserEmail(
                loginRequest.getUserEmail()
            );

            boolean isAuthenticated = loginRequest.getUserPassword().equals(user.getUserPassword());

            if (isAuthenticated) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}

