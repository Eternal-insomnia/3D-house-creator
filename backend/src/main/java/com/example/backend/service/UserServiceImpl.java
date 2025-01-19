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

    // @Autowired
    // private ModelMapper mapper;

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

    // // Получение пользователя по ID
    // @Override
    // @Transactional(readOnly = true)
    // public UserResponse findById(UUID userId) {
    //     return userRepository.findById(userId)
    //             .map(this::buildUserResponse)
    //             .orElseThrow(() -> new EntityNotFoundException("User " + userId + " is not found"));
    // }

    // // Получение пользователя по Email
    // @Override
    // public UserResponse findUserByUserEmail(String userEmail) {
    //     Optional<User> userByUserEmail = userRepository.findUserByUserEmail(userEmail);
    //     UserResponse userResponse = mapper.map(userByUserEmail, UserResponse.class);
    //     return userResponse;
    // }

    // // Добавление нового пользователя
    // @Override
    // @Transactional
    // public UserResponse create(CreateUserRequest request) {
    //     User user = buildUserRequest(request);
    //     return buildUserResponse(userRepository.save(user));
    // }

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

    // // Конвертация объекта из User в UserResponse
    // private UserResponse buildUserResponse(User user) {
    //     return new UserResponse()
    //             .setUserId(user.getUserId())
    //             .setUserName(user.getUserName())
    //             .setUserSurname(user.getUserSurname())
    //             .setUserEmail(user.getUserEmail())
    //             .setUserPassword(user.getUserPassword());
    // }

    // // Конвертация объекта из CreateUserRequest в User
    // private User buildUserRequest(CreateUserRequest request) {
    //     return new User()
    //             .setUserId(request.getUserId())
    //             .setUserName(request.getUserName())
    //             .setUserSurname(request.getUserSurname())
    //             .setUserEmail(request.getUserEmail())
    //             .setUserPassword(request.getUserPassword());
    // }

    // // Изменение полей в найденном объекте
    // private void userUpdate(User user, CreateUserRequest request) {
    //     ofNullable(request.getUserName()).map(user::setUserName);
    //     ofNullable(request.getUserSurname()).map(user::setUserSurname);
    //     ofNullable(request.getUserEmail()).map(user::setUserEmail);
    //     ofNullable(request.getUserPassword()).map(user::setUserPassword);
    // }
}
