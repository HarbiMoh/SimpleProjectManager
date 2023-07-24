package com.example.ProjectManager.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Entity.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    public Optional<Project> findByAllOwnerAndId(User owner, Long id);

    @Query("SELECT p FROM Project p JOIN p.owner u WHERE u.username = :username AND p.id = :id")
    public Optional<Project> findByUsernameAndId(String username, Long id);
}
