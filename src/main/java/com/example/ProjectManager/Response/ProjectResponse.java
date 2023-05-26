package com.example.ProjectManager.Response;

import java.util.List;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Entity.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {

    public static ProjectResponse mapToProjectResponse(Project project){
        ProjectResponse projectResponse = builder()
        .id(project.getId())
        .name(project.getName())
        .owner(UserResponse.mapToUserResponse(project.getOwner()))
        .cost(ProjectResponse.calculateCost(project.getTasks()))
        .tasks(project.getTasks() == null? null : TaskResponse.mapToTaskResponseList(project.getTasks()))
        .id(project.getId())
        .build();
    
        return projectResponse;
    }
    public static Long calculateCost(List<Task> tasks){
        long cost = 0L;
        if(tasks != null){
            for(int i = 0; i < tasks.size(); i++){
                if(tasks.get(i).getResources() != null){
                    for(int j = 0; j < tasks.get(i).getResources().size(); j++){
                        cost += tasks.get(i).getResources().get(j).getCost();
                    }
                }
            }
        }
        return cost;
    }
    private Long id;
    private String name;
    private Long cost;
    private UserResponse owner;
    private List<TaskResponse> tasks;
    
}
