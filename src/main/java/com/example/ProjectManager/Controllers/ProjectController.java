package com.example.ProjectManager.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectManager.Request.ProjectEditRequest;
import com.example.ProjectManager.Request.ProjectRequest;
import com.example.ProjectManager.Response.ProjectResponse;
import com.example.ProjectManager.Service.ProjectService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/{username}/project")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    
    @GetMapping
    public String test(){

        return "test";
    }

    @GetMapping("{projectId}")
    public ProjectResponse getProject(@PathVariable String username, @PathVariable Long projectId){
        return projectService.getProject(username, projectId);
    }

    @PostMapping("create")
    public ResponseEntity<ProjectResponse> createProject(@PathVariable String username, @Valid @RequestBody ProjectRequest request){
        return projectService.createProject(username, request);
    }

    @PostMapping("{projectId}/edit")
    public ResponseEntity<ProjectResponse> editProject(@PathVariable Long projectId, @RequestBody ProjectEditRequest request){
        return projectService.editProject(projectId, request);
    }

    @GetMapping("{projectId}/delete")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId){
        return projectService.deleteProject(projectId);
    }

}
