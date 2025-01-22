package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entity.Project;
import com.example.backend.exception.ThereIsNoSuchProjectException;
import com.example.backend.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> fetchProjectList() {
        return (List<Project>) projectRepository.findAll();
    }

    @Override
    public Optional<Project> fetchProjectById(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            throw new ThereIsNoSuchProjectException();
        }
        return project;
    }

    // @Override
    // public Project updateProject(Project project, Long projectId) {
    //     Project projectDB = projectRepository.findById(projectId).get();

    //     if (Objects.nonNull(project.getUser())) {

    //     }
    //     return projectRepository.save(projectDB);
    // }

    @Override
    public void deleteProjectById(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
