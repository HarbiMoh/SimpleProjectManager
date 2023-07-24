package com.example.ProjectManager.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    
    @Query("SELECT t FROM Task t WHERE t.parent = null")
    List<Task> findAllByProject(Project project);
    Optional<Task> findByIdAndProject(Long id, Project project);
}
