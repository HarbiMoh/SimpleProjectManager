package com.example.ProjectManager.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ProjectManager.Entity.TaskResource;

public interface TaskResourcesRepository extends JpaRepository<TaskResource, Long> {
    
    @Query("SELECT r FROM TaskResource r JOIN r.task t WHERE t.id = :taskId")
    public List<TaskResource> findAllByTaskId(@Param("taskId") Long taskId);
}
