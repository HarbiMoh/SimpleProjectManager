package com.example.ProjectManager.Response;

import java.util.List;

import com.example.ProjectManager.Entity.ResourceTypeEnum;
import com.example.ProjectManager.Entity.TaskResource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TaskResourceResponse {
    
    private Long project_id;
    private Long task_id;
    private long resource_id;
    private ResourceTypeEnum resource_type;
    private Long cost;


    public static TaskResourceResponse mapToTaskResourcesResponse(TaskResource taskResource){
        return builder()
            .project_id(taskResource.getProject().getId())
            .resource_id(taskResource.getResource().getId())
            .task_id(taskResource.getTask().getId())
            .resource_type(taskResource.getResource().getType())
            .cost(taskResource.getCost())
            .build();
        }

    
    public static List<TaskResourceResponse> mapToTaskResourcesResponseList(List<TaskResource> taskResources){
        return taskResources.stream().map(taskResource -> mapToTaskResourcesResponse(taskResource)).toList();
        }


}
