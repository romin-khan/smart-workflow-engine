package com.romin.smart_workflow_engine.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.romin.smart_workflow_engine.entity.Task;
import com.romin.smart_workflow_engine.enums.Status;
import com.romin.smart_workflow_engine.exceptions.InvalidTaskException;
import com.romin.smart_workflow_engine.exceptions.TaskNotFoundException;
import com.romin.smart_workflow_engine.repository.TaskRepo;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task createTask(Task task) {
        if(task.getTitle() == null || task.getTitle().isBlank()) {
            throw new InvalidTaskException("Task title cannot be empty");
        }
        task.setStatus(Status.TODO);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        return taskRepo.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task updateTask(@NonNull Long id, Task task) {
       Task existingTask = getTaskByIdOrThrow(id);

        Status oldStatus = existingTask.getStatus();

        if(task.getTitle() != null) {
            if(task.getTitle().isBlank()) {
                throw new InvalidTaskException("Task title cannot be empty");
            }
            existingTask.setTitle(task.getTitle());
        }
        
        if(task.getDescription() != null) {
            existingTask.setDescription(task.getDescription());
        }

        if(task.getPriority() != null) {
            if(oldStatus == Status.DONE) {
                throw new InvalidTaskException("Cannot change priority of a completed task");
            }
            if(existingTask.getPriority() != task.getPriority()) {
            existingTask.setPriority(task.getPriority());
            }
        }

        existingTask.setUpdatedAt(LocalDateTime.now());

        return taskRepo.save(existingTask);
    }

    public void deleteTask(@NonNull Long id) {
        Task existingTask = getTaskByIdOrThrow(id);
        taskRepo.delete(existingTask);
    }

    public Task getTaskById(@NonNull Long id) {
        return getTaskByIdOrThrow(id);
    }

    @NonNull
    public Task getTaskByIdOrThrow(@NonNull Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return task;
    }

    public Task updateStatus(@NonNull Long id,Status status) {

        Task existingTask = getTaskByIdOrThrow(id);

        if(existingTask.getStatus() == status) {
            throw new InvalidTaskException("Task is already in " + status + " status");
        }

        if(!existingTask.getStatus().canTransitionTo(status)) {
            throw new InvalidTaskException("Cannot transition from " + existingTask.getStatus() + " to " + status);
        }

        existingTask.setStatus(status);
        existingTask.setUpdatedAt(LocalDateTime.now());
        return taskRepo.save(existingTask);
    }
}