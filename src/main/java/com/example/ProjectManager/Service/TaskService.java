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
import com.example.ProjectManager.Response.TaskResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public List<TaskResponse> getAllTasks(Project project){
        List<Task> tasks = taskRepository.findByProject(project);
        return TaskResponse.mapToTaskResponseList(tasks);

    }

    public ResponseEntity<String> deleteTask(Long taskId){
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            taskRepository.delete(task.get());
            return ResponseEntity.ok().body("deleted");
        }
        return ResponseEntity.notFound().build();
    }

    public TaskResponse createTask(Project project, TaskRequest request){
        Task task = Task.builder()
            .name(request.getName())
            .start(request.getStart())
            .end(request.getEnd())
            .task_order(request.getOrder())
            .project(project)
            .type(request.getType())
            .resources(request.getResources())
            .build();
        task = taskRepository.save(task);
        return TaskResponse.mapToTaskResponse(task);
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
