package com.example.workouttracker.model.exception;

public class WorkoutNotFoundException extends RuntimeException {
    public WorkoutNotFoundException(Long workoutId) {
        super(String.format("Workout with id %d was not found", workoutId));
    }
}
