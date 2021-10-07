package com.example.workouttracker.api;

import com.example.workouttracker.model.Exercise;
import com.example.workouttracker.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

////    @GetMapping("/workouts/{id}/exercises")
//////    public Exercise getExerciseById(@PathVariable Long id) {
//////        return exerciseService.getExerciseById(id);
//////    }
////
////    @GetMapping("/workouts/{workoutId}/exercises")
////    public List<Exercise> getAllExercisesForWorkoutId(@PathVariable Long workoutId) {
////        return exerciseService.getAllExercisesForWorkoutId(workoutId);
////    }
////
////    @PostMapping("/workouts/{workoutId}/exercises")
////    public Exercise addExerciseForWorkout(@PathVariable Long workoutId, @RequestBody Exercise exercise) {
////        return exerciseService.addExerciseForWorkout(workoutId, exercise);
////    }
////
////    @PutMapping("/update/{id}")
////    public Exercise updateExercise(@PathVariable Long id, @RequestBody Exercise exercise) {
////        return exerciseService
////                .updateExerciseById(id, exercise)
////                .orElse(null);
////    }
////
////    @DeleteMapping("/delete/{id}")
////    public void deleteExerciseById(@PathVariable Long id) {
////        exerciseService.deleteExerciseById(id);
////    }
}
