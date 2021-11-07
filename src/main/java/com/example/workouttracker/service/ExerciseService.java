package com.example.workouttracker.service;

import com.example.workouttracker.model.Exercise;
import com.example.workouttracker.repository.ExerciseRepository;
import com.example.workouttracker.repository.ExerciseTemplatesRepository;
import com.example.workouttracker.repository.SetRepository;
import com.example.workouttracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final SetRepository setRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository, SetRepository setRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.setRepository = setRepository;
    }

    public ArrayList<Exercise> getAllExercisesForWorkout(Long workoutId) {
        return exerciseRepository.findExercisesById(workoutId);
    }

    public Exercise getExerciseById(Long id) {
        return exerciseRepository
                .findById(id)
                .orElse(null);
    }

    public List<Exercise> getAllExercisesForWorkoutId(Long workoutId) {
        return exerciseRepository.findAll();
    }

    public Exercise addExerciseForWorkout(Long workoutId, Exercise exercise) {
        workoutRepository.getById(workoutId).addExercise(exercise);
        return exerciseRepository.save(exercise);
    }

    public void deleteExerciseById(long id) {
        exerciseRepository.deleteById(id);
    }

    public Optional<Exercise> updateExerciseById(long id, Exercise exercise) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isPresent()) {
            exerciseRepository.deleteById(id);
            exerciseRepository.save(exercise);
        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }


//    @Transactional
//    public void deleteSetById(Long setId) {
//        setRepository.deleteSetById(setId);
//    }

//    public Set getSetFromExerciseById(Long exerciseId, Long setId) {
//        return this.exerciseRepository.getSetFromExerciseById(exerciseId, setId);
//    }


}
