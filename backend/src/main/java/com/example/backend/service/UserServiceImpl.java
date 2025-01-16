package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.models.CreateUserRequest;
import com.example.backend.models.UserResponse;
import com.example.backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

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

    // Получение пользователя по Email
    @Override
    @Transactional(readOnly = true)
    public UserResponse findUserByUserEmail(String userEmail) {
        Optional<User> userByUserEmail = userRepository.findUserByUserEmail(userEmail);
        UserResponse userResponse = mapper.map(userByUserEmail, UserResponse.class);
        return userResponse;
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
}
