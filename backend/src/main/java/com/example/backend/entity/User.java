package com.example.backend.entity;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private UUID userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_surname", nullable = false)
    private String userSurname;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    // Сравнение объектов класса User
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userEmail.equals(user.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail);
    }

    @Override
    public String toString() {
        return "User{" + 
                "userId='" + userId + '\'' +
                "userName='" + userName + '\'' +
                "userSurname='" + userSurname + '\'' +
                "userEmail='" + userEmail + '\'' +
                "userPassword='" + userPassword + '\'' +
                "}";
    }
}
