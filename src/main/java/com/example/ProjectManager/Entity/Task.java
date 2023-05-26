package com.example.ProjectManager.Entity;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tasks")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = true, length = 50)
    @ColumnDefault(value = "'untitled'")
    String name;


    Date start;
    Date end;

    @ManyToOne
    Project project;

    @ManyToOne
    private Task parent;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskResource> resources;

    @Enumerated(EnumType.STRING)
    @Default
    private TaskTypeEnum type = TaskTypeEnum.TASK;

    Integer task_order;
}