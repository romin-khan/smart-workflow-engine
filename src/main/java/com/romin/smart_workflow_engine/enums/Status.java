package com.romin.smart_workflow_engine.enums;

public enum Status {
    TODO,
    IN_PROGRESS,
    DONE;

    public boolean canTransitionTo(Status newStatus) {
        if(this == Status.TODO && newStatus == Status.IN_PROGRESS) return true;
        if(this == Status.IN_PROGRESS && newStatus == Status.DONE) return true;
        if(this == newStatus) return true;

        return false;
    }
}