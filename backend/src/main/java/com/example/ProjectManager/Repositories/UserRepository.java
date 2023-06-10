package com.example.ProjectManager.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ProjectManager.Entity.User;


public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE (u.username = :accountId OR u.email = :accountId) AND u.password = :password")
    Optional<User> findByUsernameOrEmailAndPassword(String accountId, String password);
    
}
