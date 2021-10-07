package com.example.workouttracker.repository;

import com.example.workouttracker.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    public ArrayList<Exercise> findExercisesById(Long workoutId);
}
