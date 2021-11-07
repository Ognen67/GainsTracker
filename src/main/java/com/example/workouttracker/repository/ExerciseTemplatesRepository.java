package com.example.workouttracker.repository;

import com.example.workouttracker.model.ExerciseTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseTemplatesRepository extends JpaRepository<ExerciseTemplate, Long> {
}
