package com.example.ProjectManager.Request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddTaskResourcesRequest {
    
    @NotEmpty
    private List<Long> resourcesId;
    @NotNull
    private List<Long> costs;
    

}
