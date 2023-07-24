package com.example.ProjectManager.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Entity.Resource;
import com.example.ProjectManager.Entity.Task;
import com.example.ProjectManager.Entity.TaskResource;
import com.example.ProjectManager.Repositories.ProjectRepository;
import com.example.ProjectManager.Repositories.ResourceRepository;
import com.example.ProjectManager.Repositories.TaskRepository;
import com.example.ProjectManager.Repositories.TaskResourcesRepository;
import com.example.ProjectManager.Request.AddTaskResourcesRequest;
import com.example.ProjectManager.Request.TaskResourceEditRequest;
import com.example.ProjectManager.Response.TaskResourceResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskResourceService {

    
    // private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ResourceRepository resourceRepository;
    private final TaskResourcesRepository taskResourcesRepository;   
    
    
    public List<TaskResourceResponse> getAllTaskResources(Long taskId){
        List<TaskResource> taskResources = taskResourcesRepository.findAllByTaskId(taskId);
        return taskResources.isEmpty()? null : TaskResourceResponse.mapToTaskResourcesResponseList(taskResources);
    }

    public List<TaskResourceResponse> addResource(String username, Long projectId, Long taskId, AddTaskResourcesRequest request){
        Optional<Project> project = projectRepository.findByUsernameAndId(username, projectId);
        if(project.isPresent()){
            Optional<Task> task = taskRepository.findByIdAndProject(taskId, project.get());
            List<Resource> resources = resourceRepository.findByProjectAndIds(project.get(), request.getResourcesId());

            if(task.isPresent() && !resources.isEmpty()){
                // task.get().setResources(resources);
                List<TaskResource> taskResources = new LinkedList<>();
                for(int i = 0; i < resources.size(); i++){
                    TaskResource taskResource = taskResourcesRepository.save(
                        TaskResource.builder()
                            .resource(resources.get(i))
                            .project(project.get())
                            .task(task.get())
                            .cost(request.getCosts().size() > i? request.getCosts().get(i) : 0L)
                            .build()
                        );
                    taskResources.add(taskResource);
                }
                return  TaskResourceResponse.mapToTaskResourcesResponseList(taskResources);
            }
        }
        return null;
    }

    public ResponseEntity<String> deleteTaskResource(Long taskResourceId){
        Optional<TaskResource> taskResource = taskResourcesRepository.findById(taskResourceId);
        if(taskResource.isPresent()){
            taskResourcesRepository.delete(taskResource.get());
            return ResponseEntity.ok().body("deleted");
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<TaskResourceResponse> editTaskResource(Long taskResourceId, TaskResourceEditRequest request){
        Optional<TaskResource> optionalTaskResource = taskResourcesRepository.findById(taskResourceId);
        if(optionalTaskResource.isPresent()){
            TaskResource taskResource = optionalTaskResource.get();
            if(request.getCost() != null)
                taskResource.setCost(request.getCost());
            taskResourcesRepository.save(taskResource);
            return ResponseEntity.ok().body(TaskResourceResponse.mapToTaskResourcesResponse(taskResource));
        }
        return ResponseEntity.notFound().build();
    }
}
