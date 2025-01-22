package com.example.backend.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @Column(name = "userId")
    @GeneratedValue
    private UUID userId;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "userSurname", nullable = false)
    private String userSurname;

    @Column(name = "userEmail", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "userPassword", nullable = false)
    private String userPassword;

    @Column(name = "userRole", nullable = false)
    private String userRole;

    // Подключение проектов пользователя
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL
    )
    private List<Project> userProjects = new ArrayList<Project>();

    public void addProject(Project project) {
        userProjects.add(project);
        project.setUser(this);
    }
    
    public void removeProject(Project project) {
        userProjects.remove(project);
        project.setUser(null);
    }

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
        return "{" + 
                "userId='" + userId + "'," +
                "userName='" + userName + "'," +
                "userSurname='" + userSurname + "'," +
                "userEmail='" + userEmail + "'," +
                "userPassword='" + userPassword + "'" +
                "userRole='" + userRole + "'" +
                "}";
    }
}
