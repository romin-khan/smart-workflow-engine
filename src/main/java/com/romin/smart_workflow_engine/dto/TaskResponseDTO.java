package com.romin.smart_workflow_engine.dto;

import java.time.LocalDateTime;

import com.romin.smart_workflow_engine.enums.Priority;
import com.romin.smart_workflow_engine.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TaskResponseDTO {

    private Long id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
