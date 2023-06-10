package com.example.ProjectManager.Request;

import com.example.ProjectManager.Entity.ResourceTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceEditRequest {
    
    private String name;
    private ResourceTypeEnum type;
}
