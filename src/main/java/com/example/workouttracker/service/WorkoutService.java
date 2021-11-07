package com.example.workouttracker.service;

import com.example.workouttracker.model.Exercise;
import com.example.workouttracker.model.Workout;
import com.example.workouttracker.model.exception.ExerciseNotFoundException;
import com.example.workouttracker.model.exception.WorkoutNotFoundException;
import com.example.workouttracker.repository.ExerciseRepository;
import com.example.workouttracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
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

    public Exercise getExerciseForWorkout(Long workoutId,
                                          Long exerciseId) {
        Workout workout = this.workoutRepository.findById(workoutId).orElseThrow(() -> new WorkoutNotFoundException(workoutId));
        return workout.getExercises().stream().filter(e -> e.getId().equals(exerciseId)).findFirst().orElseThrow(() -> new ExerciseNotFoundException(exerciseId));
    }

//    public Set getSetForExerciseById(Long exerciseId,
//                                     Long setId) {
//        return this.exerciseRepository.getSetFromExerciseById(exerciseId, setId);
//    }
}
