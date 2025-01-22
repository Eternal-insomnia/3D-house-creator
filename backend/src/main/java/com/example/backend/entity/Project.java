package com.example.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Project {

    @Id
    @Column(name = "projectId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
