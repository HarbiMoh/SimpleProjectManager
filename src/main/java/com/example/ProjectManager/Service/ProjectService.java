package com.example.ProjectManager.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Entity.Task;
import com.example.ProjectManager.Entity.User;
import com.example.ProjectManager.Repositories.ProjectRepository;
import com.example.ProjectManager.Repositories.TaskRepository;
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
    private final TaskRepository taskRepository;
    
    public ResponseEntity<ProjectResponse> createProject(String username, ProjectRequest request){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            Project project = Project.builder().name(request.getName()).owner(user.get()).build();
            projectRepository.save(project);
            return ResponseEntity.created(null).body(ProjectResponse.mapToProjectResponse(project, new LinkedList<Task>())) ;
        }
        else{
            return null;
        }
    }

    public ProjectResponse getProject(String username, Long projectId){
        Optional<Project> project = projectRepository.findByUsernameAndId(username, projectId);
        List<Task> tasks = taskRepository.findAllByProject(project.get());
        return project.isPresent()? ProjectResponse.mapToProjectResponse(project.get(), tasks) : null;
    }

    public ResponseEntity<ProjectResponse> editProject(Long projectId, ProjectEditRequest request){
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if(optionalProject.isPresent()){
            Project project = optionalProject.get();
            if(request.getName() != null)
                project.setName(request.getName());
            project = projectRepository.save(project);
            return ResponseEntity.ok().body(ProjectResponse.mapToProjectResponse(project, new LinkedList<Task>()));
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
