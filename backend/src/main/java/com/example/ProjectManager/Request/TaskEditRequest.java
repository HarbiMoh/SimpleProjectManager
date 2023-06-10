package com.example.ProjectManager.Request;

import java.sql.Date;

import com.example.ProjectManager.Entity.TaskTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TaskEditRequest {
    
    private String name;
    private Integer order;
    private Date start;
    private Date end;
    private TaskTypeEnum type;
}
