package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.models.CreateUserRequest;
import com.example.backend.models.UserResponse;
import com.example.backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    // Получение всех пользователей
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }

    // Получение пользователя по ID
    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(UUID userId) {
        return userRepository.findById(userId)
                .map(this::buildUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("User " + userId + " is not found"));
    }

    // Добавление нового пользователя
    @Override
    @Transactional
    public UserResponse create(CreateUserRequest request) {
        User user = buildUserRequest(request);
        return buildUserResponse(userRepository.save(user));
    }

    // Изменение данных существующего пользователя
    @Override
    @Transactional
    public UserResponse update(UUID userId, CreateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User " + userId + " is not found"));
        userUpdate(user, request);
        return buildUserResponse(userRepository.save(user));
    }

    // Удаление пользователя
    @Override
    @Transactional
    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }

    // Конвертация объекта из User в UserResponse
    private UserResponse buildUserResponse(User user) {
        return new UserResponse()
                .setUserId(user.getUserId())
                .setUserName(user.getUserName())
                .setUserSurname(user.getUserSurname())
                .setUserEmail(user.getUserEmail())
                .setUserPassword(user.getUserPassword());
    }

    // Конвертация объекта из CreateUserRequest в User
    private User buildUserRequest(CreateUserRequest request) {
        return new User()
                .setUserId(request.getUserId())
                .setUserName(request.getUserName())
                .setUserSurname(request.getUserSurname())
                .setUserEmail(request.getUserEmail())
                .setUserPassword(request.getUserPassword());
    }

    // Изменение полей в найденном объекте
    private void userUpdate(User user, CreateUserRequest request) {
        ofNullable(request.getUserName()).map(user::setUserName);
        ofNullable(request.getUserSurname()).map(user::setUserSurname);
        ofNullable(request.getUserEmail()).map(user::setUserEmail);
        ofNullable(request.getUserPassword()).map(user::setUserPassword);
    }

    // /**
    //  * Найти пользователя по email
    //  *
    //  * @param userEmail - email пользователя
    //  * @return найденный пользователь
    //  * @throws RuntimeException, если пользователь не найден
    //  */
    // public User findUserByUserEmail(String userEmail) {
    //     Optional<User> user = userRepository.findByUserEmail(userEmail);
    //     return user.orElseThrow(() -> new RuntimeException("User with email " + userEmail + " not found"));
    // }

    // /**
    //  * Аутентифицировать пользователя и вернуть его данные
    //  *
    //  * @param userEmail    - email пользователя
    //  * @param userPassword - пароль пользователя
    //  * @return объект пользователя
    //  * @throws RuntimeException если аутентификация не удалась
    //  */
    // public User authenticateAndGetUser(String userEmail, String userPassword) {
    //     Optional<User> userOptional = userRepository.findByUserEmail(userEmail);
    //     if (userOptional.isPresent()) {
    //         User user = userOptional.get();
    //         if (user.getUserPassword().equals(userPassword)) {
    //             return user;
    //         } else {
    //             throw new RuntimeException("Invalid password");
    //         }
    //     }
    //     throw new RuntimeException("User not found");
    // }
}
