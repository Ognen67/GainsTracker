package com.example.workouttracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Set> sets;

    public Exercise() {

    }

    public Exercise(@JsonProperty("name") String name) {
        this.name = name;
        this.sets = new ArrayList<Set>();
    }

    public void addSet(Set set) {
        this.sets.add(set);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public void deleteSetForExerciseById(Long setId) {
        this.sets.removeIf(s -> s.getId().equals(setId));
    }
}
