package com.example.workouttracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "sets")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reps;
    private Double weight;

    public Set() {

    }

    public Set(@JsonProperty("reps") Long reps, @JsonProperty("weight") Double weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReps() {
        return reps;
    }

    public void setReps(Long reps) {
        this.reps = reps;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
