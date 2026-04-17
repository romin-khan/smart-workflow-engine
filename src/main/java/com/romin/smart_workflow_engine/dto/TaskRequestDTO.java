package com.romin.smart_workflow_engine.dto;

import com.romin.smart_workflow_engine.enums.Priority;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TaskRequestDTO {

    @NotBlank
    private String title;

    private String description;

    private Priority priority;
}    