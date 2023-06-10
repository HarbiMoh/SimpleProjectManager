package com.example.ProjectManager.Response;

import java.util.List;

import com.example.ProjectManager.Entity.Resource;
import com.example.ProjectManager.Entity.ResourceTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceResponse {
    
    private Long id;
    private String name;
    private ResourceTypeEnum type;
    private Long cost;
    private List<TaskResourceResponse> tasks;

    public static ResourceResponse mapToResourceResponse(Resource resource){
        return builder()
            .name(resource.getName())
            .id(resource.getId())
            .type(resource.getType())
            .cost(resource.getCost())
            .tasks(resource.getTasks() == null? null : TaskResourceResponse.mapToTaskResourcesResponseList(resource.getTasks()))
            .build();
    }
}
