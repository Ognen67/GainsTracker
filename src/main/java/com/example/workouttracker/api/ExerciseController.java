package com.example.workouttracker.api;

import com.example.workouttracker.service.ExerciseService;
import com.example.workouttracker.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final SetService setService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, SetService setService) {
        this.exerciseService = exerciseService;
        this.setService = setService;
    }

    @DeleteMapping("/{exerciseId}/set/{setId}")
    public void deleteSetById(@PathVariable Long exerciseId,
                              @PathVariable Long setId) {
        exerciseService.getExerciseById(exerciseId).deleteSetForExerciseById(setId);
        setService.deleteSetById(setId);
    }

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
