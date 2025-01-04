package com.example.backend.models;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserRequest {
    private UUID userId;
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPassword;
}
