package com.example.workouttracker.repository;

import com.example.workouttracker.model.Workout;
import com.example.workouttracker.model.WorkoutStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    public Workout findWorkoutByName(String name);

    public List<Workout> findWorkoutsByStatus(WorkoutStatus status);
}
