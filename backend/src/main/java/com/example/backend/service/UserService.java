package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public void createUser(User user) {
        userRepository.save(user);
    }
    
    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        
        return false;
    }

    public boolean editUser(User user, int id) {
        if (userRepository.existsById(id)) {
            user.setUserId(id);
            userRepository.save(user);
            return true;
        }

        return false;
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

    /**
     * Аутентифицировать пользователя и вернуть его данные
     *
     * @param userEmail    - email пользователя
     * @param userPassword - пароль пользователя
     * @return объект пользователя
     * @throws RuntimeException если аутентификация не удалась
     */
    public User authenticateAndGetUser(String userEmail, String userPassword) {
        Optional<User> userOptional = userRepository.findByUserEmail(userEmail);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getUserPassword().equals(userPassword)) {
                return user;
            } else {
                throw new RuntimeException("Invalid password");
            }
        }
        throw new RuntimeException("User not found");
    }
}
