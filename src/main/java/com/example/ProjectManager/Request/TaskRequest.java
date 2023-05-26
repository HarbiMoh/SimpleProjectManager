package com.example.ProjectManager.Request;

import java.sql.Date;
import java.util.List;

import com.example.ProjectManager.Entity.TaskResource;
import com.example.ProjectManager.Entity.TaskTypeEnum;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {

    @Default
    private String name = "untitled";
    @DecimalMin(value = "0", message = "task_order must be 0 or higher")
    @NotNull
    private Integer order;
    private Date start;
    private Date end;
    @NotNull
    private TaskTypeEnum type;
    private List<TaskResource> resources;
    
}
