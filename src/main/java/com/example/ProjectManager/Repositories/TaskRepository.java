package com.example.ProjectManager.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    
    List<Task> findByProject(Project project);
    Optional<Task> findByIdAndProject(Long id, Project project);
}
