package com.example.workouttracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Entity(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Exercise> exercises;

    @Enumerated(EnumType.STRING)
    private WorkoutStatus status;
    private LocalTime time;

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public Workout() {
    }

    public Workout(@JsonProperty("name") String name) {
        this.name = name;
        this.exercises = new ArrayList<Exercise>();
        this.status = WorkoutStatus.STARTED;
    }

    public Workout(@JsonProperty("name") String name,
                   @JsonProperty("exercises") List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
        this.status = WorkoutStatus.STARTED;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkoutStatus getStatus() {
        return status;
    }

    public void setStatus(WorkoutStatus status) {
        this.status = status;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
