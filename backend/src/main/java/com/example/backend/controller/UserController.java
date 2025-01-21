package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.validation.Valid;

/**
 * REST controller for managing User entities.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private UserService userService;

    /**
     * Handles POST requests to save a new user.
     * @param user the user entity to be saved
     * @return the saved user entity
     */
    @PostMapping("/users")
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * Handles GET requests to fetch the list of all users.
     * @return a list of user entities
     */
    @GetMapping("/users")
    public List<User> fetchUserList() {
        return userService.fetchUserList();
    }

    @GetMapping("/users/email")
    public ResponseEntity<String> fetchUserByEmail(@RequestParam("userEmail") String userEmail) {
        User user = userService.fetchUserByEmail(userEmail).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(user.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/{id}")
    public Optional<User> fetchUserById(@PathVariable("id") UUID userId) {
        return userService.fetchUserById(userId);
    }

    /**
     * Handles PUT requests to update an existing user.
     * @param user the user entity with updated info
     * @param userId the UUID of the user to be updated
     * @return the updated user entity
     */
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") UUID userId) {
        return userService.updateUser(user, userId);
    }

    /**
     * Handles DELETE requests to remove a user by UUID.
     * @param userId the UUID of the user to be deleted
     * @return a success message
     */
    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") UUID userId) {
        userService.deleteUserById(userId);
        return "Deleted Successfully";
    }
}

