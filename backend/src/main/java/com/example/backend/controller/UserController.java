package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.backend.models.CreateUserRequest;
import com.example.backend.models.UserResponse;
import com.example.backend.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Получение всех пользователей
    @GetMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    // Получение пользователя по ID
    @GetMapping(value = "/{userid}", produces = APPLICATION_JSON_VALUE)
    public UserResponse findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    // Добавление нового пользователя
    @PostMapping(value = "/registration", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserResponse create(@RequestBody CreateUserRequest request) {
        return userService.create(request);    
    } 

    // Изменение данных существующего пользователя
    @PatchMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserResponse update(@PathVariable UUID userId, @RequestBody CreateUserRequest request) {
        return userService.update(userId, request);
    }

    // Удаление пользователя
    @ResponseStatus(HttpStatus.NO_CONTENT) // Возврат статуса вместо объекта
    @DeleteMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable UUID userId) {
        userService.delete(userId);
    }

    // /**
    //  * Эндпоинт для проверки пользователя по email и паролю
    //  *
    //  * @param loginRequest - объект с полями userEmail и userPassword
    //  * @return подтверждение успешного входа или ошибка
    //  */
    // @PostMapping("/login")
    // public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
    //     try {
    //         User user = userService.authenticateAndGetUser(
    //             loginRequest.getUserEmail(), 
    //             loginRequest.getUserPassword()
    //         );

    //         // Если пользователь найден, возвращаем его данные
    //         return ResponseEntity.ok(user);
    //     } catch (RuntimeException e) {
    //         // Если ошибка, возвращаем сообщение
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    //             .body(new ErrorResponse("Login failed: " + e.getMessage()));
    //     }
    // }
}

