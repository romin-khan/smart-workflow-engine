package com.romin.smart_workflow_engine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romin.smart_workflow_engine.dto.TaskRequestDTO;
import com.romin.smart_workflow_engine.dto.TaskResponseDTO;
import com.romin.smart_workflow_engine.dto.UpdateStatusDTO;
import com.romin.smart_workflow_engine.entity.Task;
import com.romin.smart_workflow_engine.service.TaskService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponseDTO createTask(@Valid @RequestBody TaskRequestDTO dto) {
        Task task = mapToEntity(dto);
        Task saved = taskService.createTask(task);
        return mapToResponse(saved);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return mapToResponse(taskService.getTaskById(id));
    }

    @GetMapping
    public List<TaskResponseDTO> listTasks() {
        return taskService.getAllTasks()
            .stream()
            .map(this::mapToResponse)
            .toList();
    }

    @PatchMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequestDTO dto) {
        Task task = mapToEntity(dto);
        Task updated = taskService.updateTask(id, task);
        return mapToResponse(updated);
    }

    @PatchMapping("/{id}/status")
    public TaskResponseDTO updateStatus(
        @PathVariable Long id,
        @Valid @RequestBody UpdateStatusDTO dto
    ) {
        Task updated = taskService.updateStatus(id, dto.getStatus());
        return mapToResponse(updated);
    }

    private Task mapToEntity(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        return task;
    }

    private TaskResponseDTO mapToResponse(Task task) {
        return new TaskResponseDTO(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus(),
            task.getPriority(),
            task.getCreatedAt(),
            task.getUpdatedAt()
        );
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
