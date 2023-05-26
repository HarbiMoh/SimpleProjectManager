package com.example.ProjectManager.Request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResourceEditRequest {
    
    @Min(0)
    private Long cost;
}
