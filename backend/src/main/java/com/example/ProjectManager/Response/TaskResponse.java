package com.example.ProjectManager.Response;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import com.example.ProjectManager.Entity.ResourceTypeEnum;
import com.example.ProjectManager.Entity.Task;
import com.example.ProjectManager.Entity.TaskResource;
import com.example.ProjectManager.Entity.TaskTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {
    
    private Long id;
    private String name;
    private Date start;
    private Date end;
    private List<TaskResourceResponse> resources;
    private TaskTypeEnum type;
    private Integer order;
    private Long cost;

    public static TaskResponse mapToTaskResponse(Task task){
        if(task == null)
            return null;
        return builder()
            .id(task.getId())
            .name(task.getName())
            .order(task.getTask_order())
            .start(task.getStart())
            .end(task.getEnd())
            .type(task.getType())
            .cost(calculateCost(task.getResources(), task.getStart(), task.getEnd()))
            .resources(task.getResources() != null? task.getResources().stream().map(resource -> TaskResourceResponse.mapToTaskResourcesResponse(resource)).toList() : null)
            .build();
    }
    public static List<TaskResponse> mapToTaskResponseList(List<Task> tasks){
        List<TaskResponse> result = new LinkedList<>();
        tasks.forEach(task -> result.add(mapToTaskResponse(task)));
        return result;
    }

    private static Long calculateCost(List<TaskResource> taskResources, Date start, Date end){
        long cost = 0L;
        long days = 0L;
        final int dayInSeconds = 86_400_000;
        if(start != null && end != null)
            days = (end.getTime() - start.getTime())/dayInSeconds;
        if(taskResources != null){
            for(int i = 0; i < taskResources.size(); i++){
                long taskResourceCost = taskResources.get(i).getCost();
                if(taskResources.get(i).getResource().getType() == ResourceTypeEnum.WORK && days > 0){
                    taskResourceCost *= days*8;
                }
                cost += taskResourceCost;
            }
            }
        return cost;
    }
}
