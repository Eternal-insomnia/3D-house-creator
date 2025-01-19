package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        // Saves and returns the user entity
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchUserList() {
        // Retrieves and returns a list of all user entities
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> fetchUserById(UUID userId) {
        // Retrieves and returns user entity by UUID
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> fetchUserByEmail(String userEmail) {
        // Retrieves and returns user entity by Email
        return userRepository.findUserByUserEmail(userEmail);
    }

    @Override
    public User updateUser(User user, UUID userId) {
        // Finds the existing user by UUID
        User userDB = userRepository.findById(userId).get();

        // Updates fields if they are not null or empty
        if (Objects.nonNull(user.getUserName()) && !"".equalsIgnoreCase(user.getUserName())) {
            userDB.setUserName(user.getUserName());
        }
        if (Objects.nonNull(user.getUserSurname()) && !"".equalsIgnoreCase(user.getUserSurname())) {
            userDB.setUserSurname(user.getUserSurname());
        }
        if (Objects.nonNull(user.getUserEmail()) && !"".equalsIgnoreCase(user.getUserEmail())) {
            userDB.setUserEmail(user.getUserEmail());
        }
        if (Objects.nonNull(user.getUserPassword()) && !"".equalsIgnoreCase(user.getUserPassword())) {
            userDB.setUserPassword(user.getUserPassword());
        }
        
        // Saves and returns the updated user entity
        return userRepository.save(userDB);
    }

    // Удаление пользователя
    @Override
    public void deleteUserById(UUID userId) {
        // Deletes the user by its UUID
        userRepository.deleteById(userId);
    }
}
