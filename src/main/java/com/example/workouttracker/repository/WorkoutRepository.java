package com.example.workouttracker.repository;

import com.example.workouttracker.model.Exercise;
import com.example.workouttracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {


}
