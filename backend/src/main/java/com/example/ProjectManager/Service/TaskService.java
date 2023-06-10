package com.example.ProjectManager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Entity.Task;
import com.example.ProjectManager.Repositories.ProjectRepository;
import com.example.ProjectManager.Repositories.TaskRepository;
import com.example.ProjectManager.Request.TaskEditRequest;
import com.example.ProjectManager.Request.TaskRequest;
import com.example.ProjectManager.Response.TaskOrgnizedResponse;
import com.example.ProjectManager.Response.TaskResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskResourceService taskResourceService;

    public List<TaskOrgnizedResponse> getAllTasks(Project project){
        List<Task> tasks = taskRepository.findAllByProject(project);
        return TaskOrgnizedResponse.mapToTaskOrgnizedResponseList(tasks);

    }

    public ResponseEntity<String> deleteTask(Long taskId){
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            taskRepository.delete(task.get());
            return ResponseEntity.ok().body("deleted");
        }
        return ResponseEntity.notFound().build();
    }

    // TODO: fix the response (response resouces and cost are wrong)
    public TaskResponse createTask(Project project, TaskRequest request){
        Optional<Task> optionalParent = Optional.ofNullable(null);
        if(request.getParentId() != null)
            optionalParent = taskRepository.findById(request.getParentId());
        Task task = Task.builder()
            .name(request.getName())
            .start(request.getStart())
            .end(request.getEnd())
            .task_order(request.getOrder())
            .project(project)
            .type(request.getType())
            .parent(optionalParent.isPresent()? optionalParent.get() : null)
            .build();
        task = taskRepository.save(task);
        if(request.getResources() != null)
            taskResourceService.addResource(project.getOwner().getUsername(), task.getProject().getId(), task.getId(), request.getResources());
        Optional<Task> optionalTask = taskRepository.findById(task.getId());
        if(optionalTask.isPresent())
            return TaskResponse.mapToTaskResponse(optionalTask.get());
        return null;
    }

    public TaskResponse editTask(String username, Long projectId, Long taskId, TaskEditRequest request){
        Optional<Project> project = projectRepository.findByUsernameAndId(username, projectId);
        if(project.isPresent()){
            Optional<Task> OptionalTask = taskRepository.findByIdAndProject(taskId, project.get());
            if(OptionalTask.isPresent()){
                Task task = OptionalTask.get();
                if(request.getName() != null)
                    task.setName(request.getName());
                if(request.getEnd() != null)
                    task.setEnd(request.getEnd());
                if(request.getStart() != null)
                    task.setStart(request.getStart());
                if(request.getOrder() != null)
                    task.setTask_order(request.getOrder());
                if(request.getType() != null)
                    task.setType(request.getType());
                taskRepository.save(task);
                return TaskResponse.mapToTaskResponse(task);
            }
        }  
        return null;
    }
 
}
