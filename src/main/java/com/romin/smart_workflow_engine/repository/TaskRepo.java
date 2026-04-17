package com.romin.smart_workflow_engine.repository;

import com.romin.smart_workflow_engine.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
    
}
