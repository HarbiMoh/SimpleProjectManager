package com.example.ProjectManager.Request;

import com.example.ProjectManager.Entity.ResourceTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceRequest {
    
    @Default
    private String name = "new resource";
    private ResourceTypeEnum type;
}
