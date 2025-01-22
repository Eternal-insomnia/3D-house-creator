package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
} 