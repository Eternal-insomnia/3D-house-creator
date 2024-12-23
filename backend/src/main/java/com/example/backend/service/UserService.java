package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * Найти пользователя по email
     *
     * @param userEmail - email пользователя
     * @return найденный пользователь
     * @throws RuntimeException, если пользователь не найден
     */
    public User findUserByUserEmail(String userEmail) {
        Optional<User> user = userRepository.findByUserEmail(userEmail);
        return user.orElseThrow(() -> new RuntimeException("User with email " + userEmail + " not found"));
    }
}
