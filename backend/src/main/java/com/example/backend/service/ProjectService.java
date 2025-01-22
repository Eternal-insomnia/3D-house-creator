package com.example.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.backend.entity.Project;

/**
 * Service interface for Project entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface ProjectService {
    
    /**
     * Saves Project entity (create new project entity).
     * @param project the project to save
     * @return the saved project
     */
    Project saveProject(UUID userId);

    /**
     * Fetches the list of all Project entities.
     * @return a list of projects
     */
    List<Project> fetchProjectList();

    /**
     * Fetches the project entity by its ID.
     * @param projectId the ID of project to fetch
     * @return the fetched project entity
     */
    Optional<Project> fetchProjectById(Long projectId);

    // /**
    //  * Updates an existing project entity.
    //  * @param project the project with updated information
    //  * @param projectId the ID of the project to update
    //  * @return the updated project
    //  */
    // Project updateProject(Project project, Long projectId);

    /**
     * Deletes a Project entity by its ID.
     * @param projectId the ID fo the project to delete
     */
    void deleteProjectById(Long projectId);
}
