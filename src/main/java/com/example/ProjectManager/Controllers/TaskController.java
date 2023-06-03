package com.example.ProjectManager.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Repositories.ProjectRepository;
import com.example.ProjectManager.Request.AddTaskResourcesRequest;
import com.example.ProjectManager.Request.TaskEditRequest;
import com.example.ProjectManager.Request.TaskRequest;
import com.example.ProjectManager.Request.TaskResourceEditRequest;
import com.example.ProjectManager.Response.TaskOrgnizedResponse;
import com.example.ProjectManager.Response.TaskResourceResponse;
import com.example.ProjectManager.Response.TaskResponse;
import com.example.ProjectManager.Service.TaskResourceService;
import com.example.ProjectManager.Service.TaskService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/{username}/{projectId}/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskResourceService taskResourcesService;
    
    private final ProjectRepository projectRepository;
    
    @GetMapping()
    public List<TaskOrgnizedResponse> getAllTasks(@PathVariable String username, @PathVariable Long projectId){
        Optional<Project> project = projectRepository.findByUsernameAndId(username, projectId);
        return project.isPresent()? taskService.getAllTasks(project.get()) : null;
    }

    @PostMapping("/create")
    public TaskResponse createTask(@PathVariable String username, @PathVariable Long projectId, @Valid @RequestBody TaskRequest request){
        Optional<Project> project = projectRepository.findByUsernameAndId(username, projectId);
        return project.isPresent()? taskService.createTask(project.get(), request) : null; 
    }

    @GetMapping("{taskId}/resources")
    public List<TaskResourceResponse> getAllTaskResources(@PathVariable Long taskId){
        return taskResourcesService.getAllTaskResources(taskId);
    }

    @GetMapping("/{taskId}/delete")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }

    @PostMapping("{taskId}/resources/add")
    public List<TaskResourceResponse> addResources(@PathVariable String username, @PathVariable Long projectId, @PathVariable Long taskId, @Valid @RequestBody AddTaskResourcesRequest request){
        return taskResourcesService.addResource(username, projectId, taskId, request);
    }

    @PostMapping("{taskId}/edit")
    public TaskResponse editTask(@PathVariable String username, @PathVariable Long projectId, @PathVariable Long taskId, @Valid @RequestBody TaskEditRequest request){
        return taskService.editTask(username,projectId, taskId, request);
    }

    @GetMapping("{taskId}/resources/{taskResourceId}/delete")
    public ResponseEntity<String> deleteTaskResource(@PathVariable Long taskResourceId){
        return taskResourcesService.deleteTaskResource(taskResourceId);
    }

    @PostMapping("{taskId}/resources/{taskResourceId}/edit")
    public ResponseEntity<TaskResourceResponse> editTaskResource(@PathVariable Long taskResourceId, @Valid @RequestBody TaskResourceEditRequest request){
        return taskResourcesService.editTaskResource(taskResourceId, request);
    }
}
