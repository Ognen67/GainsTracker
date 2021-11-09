package com.example.workouttracker.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "workout_templates")
public class WorkoutTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Exercise> exercises;

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public WorkoutTemplate() {
    }

//    public WorkoutTemplate(@JsonProperty("name") String name) {
//        this.name = name;
//        this.exercises = new ArrayList<Exercise>();
//    }

    public WorkoutTemplate(@JsonProperty("name") String name,
                           @JsonProperty("exercises") List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
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

}
