package com.example.workouttracker.model.exception;

public class ExerciseNotFoundException extends RuntimeException{
    public ExerciseNotFoundException(Long exerciseId) {
        super(String.format("Exercise with id %d was not found", exerciseId));
    }
}
