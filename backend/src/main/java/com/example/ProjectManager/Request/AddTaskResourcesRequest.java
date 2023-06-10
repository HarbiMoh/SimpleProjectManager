package com.example.ProjectManager.Request;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddTaskResourcesRequest {
    
    private List<Long> resourcesId;
    private List<Long> costs;
    

}
