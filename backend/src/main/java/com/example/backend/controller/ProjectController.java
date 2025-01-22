package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Project;
import com.example.backend.service.ProjectService;

import jakarta.validation.Valid;

/**
 * REST controller for managing Project entities.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController
@RequestMapping("/api")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    /**
     * Handles POST requests to save a new project.
     * @param project the project entity to be saved
     * @return the saved project entity
     */
    @PostMapping("/projects")
    public Project saveProject(@Valid @RequestBody Project project) {
        return projectService.saveProject(project);
    }

    /**
     * Handles GET requests to fetch the list of all projects.
     * @return a list of project entities
     */
    @GetMapping("/projects")
    public List<Project> fetchProjectList() {
        return projectService.fetchProjectList();
    }

    /**
     * Handles GET requests to fetch project entity by its ID
     * @param projectId
     * @return
     */
    @GetMapping("/projects/{id}")
    public Optional<Project> fetchProjectById(@PathVariable("id") Long projectId) {
        return projectService.fetchProjectById(projectId);
    }

    // /**
    //  * Handles PUT requests to update an existing project.
    //  * @param project the project entity with updated info
    //  * @param projectId the ID of the project to be updated
    //  * @return the updated project entity
    //  */
    // @PutMapping("/projects/{id}")
    // public Project updateProject(@RequestBody Project project, @PathVariable("id") ID projectId) {
    //     return projectService.updateProject(project, projectId);
    // }

    /**
     * Handles DELETE requests to remove a project by ID.
     * @param projectId the ID of the project to be deleted
     * @return a success message
     */
    @DeleteMapping("/projects/{id}")
    public String deleteProjectById(@PathVariable("id") Long projectId) {
        projectService.deleteProjectById(projectId);
        return "Deleted Successfully";
    }
}
