package com.example.ProjectManager.Response;

import java.util.LinkedList;
import java.util.List;

import com.example.ProjectManager.Entity.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {


    private Long id;
    private String name;
    private UserResponse owner;
    


    public static ProjectResponse mapToProjectResponse(Project project){
        ProjectResponse projectResponse = builder()
        .id(project.getId())
        .name(project.getName())
        .owner(UserResponse.mapToUserResponse(project.getOwner()))
        .id(project.getId())
        .build();
    
        return projectResponse;
    }

    public static List<ProjectResponse> mapToProjectResponseList(List<Project> projects){
        List<ProjectResponse> result = new LinkedList<>();
        for(int i=0; i<projects.size(); i++){
            result.add(mapToProjectResponse(projects.get(i)));
        }
        return result;
    }

    // private static Long calculateCost(List<Task> tasks){
    //     long cost = 0L;
    //     if(tasks != null){
    //         for(int i = 0; i < tasks.size(); i++){
    //             if(tasks.get(i).getResources() != null){
    //                 cost += calculateTaskResoucesCost(tasks.get(i));
    //             }
    //             List<Task> subtasks = tasks.get(i).getSubtasks();
    //             if(subtasks != null && !subtasks.isEmpty()){
    //                 for(int j = 0; j < subtasks.size(); j++){
    //                     cost += calculateTaskResoucesCost(subtasks.get(j));
    //                 }
    //             }
    //         }
    //     }
    //     return cost;
    // }
    //
    // private static Long calculateTaskResoucesCost(Task task){
    //     Long cost = 0L;
    //     for(int j = 0; j < task.getResources().size(); j++){
    //         cost += task.getResources().get(j).getCost();
    //     }
    //     return cost;
    // }
}
