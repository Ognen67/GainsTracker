package com.example.workouttracker.service;

import com.example.workouttracker.model.Workout;
import com.example.workouttracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout getWorkoutById(Long id) {
        return workoutRepository
                .findById(id)
                .orElse(null);
    }

    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout addWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public void deleteWorkoutById(Long id) {
        workoutRepository.deleteById(id);
    }

    public Optional<Workout> updateWorkoutById(Long id, Workout workout) {
        Optional<Workout> workoutOptional = workoutRepository.findById(id);
        if (workoutOptional.isPresent()) {
            Workout workoutToUpdate = workoutOptional.get();
            updateProperties(workout, workoutToUpdate);
            workoutRepository.save(workoutToUpdate);
        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private void updateProperties(Workout workout, Workout workoutToUpdate) {
        workoutToUpdate.setName(workout.getName());
        workoutToUpdate.setExercises(workout.getExercises());
    }
}
