package com.example.ProjectManager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Entity.User;
import com.example.ProjectManager.Repositories.ProjectRepository;
import com.example.ProjectManager.Repositories.UserRepository;
import com.example.ProjectManager.Request.ProjectEditRequest;
import com.example.ProjectManager.Request.ProjectRequest;
import com.example.ProjectManager.Response.ProjectResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    
    public ResponseEntity<ProjectResponse> createProject(String username, ProjectRequest request){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            Project project = Project.builder().name(request.getName()).owner(user.get()).build();
            projectRepository.save(project);
            return ResponseEntity.created(null).body(ProjectResponse.mapToProjectResponse(project)) ;
        }
        else{
            return null;
        }
    }

    public ResponseEntity<List<ProjectResponse>> getAllProjects(String username){
        Optional<User> optionalOwner = userRepository.findByUsername(username);
        if(optionalOwner.isPresent()){
            List<Project> projects = projectRepository.findAllByOwner(optionalOwner.get());
            return ResponseEntity.ok().body(ProjectResponse.mapToProjectResponseList(projects));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    public ProjectResponse getProject(String username, Long projectId){
        Optional<Project> project = projectRepository.findByUsernameAndId(username, projectId);
        return project.isPresent()? ProjectResponse.mapToProjectResponse(project.get()) : null;
    }

    public ResponseEntity<ProjectResponse> editProject(Long projectId, ProjectEditRequest request){
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if(optionalProject.isPresent()){
            Project project = optionalProject.get();
            if(request.getName() != null)
                project.setName(request.getName());
            project = projectRepository.save(project);
            return ResponseEntity.ok().body(ProjectResponse.mapToProjectResponse(project));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> deleteProject(Long projectId){
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isPresent()){
            projectRepository.delete(project.get());
            return ResponseEntity.ok().body("deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
