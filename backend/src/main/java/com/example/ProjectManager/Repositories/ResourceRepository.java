package com.example.ProjectManager.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    public List<Resource> findByProject(Project project);
    public List<Resource> findByIdAndProject(Long id, Project project);

    @Query(value = "SELECT e FROM Resource e WHERE e.project = :project AND e.id IN :ids")
    public List<Resource> findByProjectAndIds(@Param("project") Project project, @Param("ids") List<Long> ids);
    
}