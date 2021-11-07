package com.example.workouttracker.repository;

import com.example.workouttracker.model.WorkoutTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutTemplatesRepository extends JpaRepository<WorkoutTemplate, Long> {
}
