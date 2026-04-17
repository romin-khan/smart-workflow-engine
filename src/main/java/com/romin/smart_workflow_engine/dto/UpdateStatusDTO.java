package com.romin.smart_workflow_engine.dto;

import com.romin.smart_workflow_engine.enums.Status;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UpdateStatusDTO {

    @NotNull
    private Status status;
    
}
