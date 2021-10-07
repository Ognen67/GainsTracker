package com.example.workouttracker.api;

import com.example.workouttracker.model.Exercise;
import com.example.workouttracker.model.Set;
import com.example.workouttracker.model.Workout;
import com.example.workouttracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("/workouts/{id}")
    public Workout getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }

    @GetMapping("/workouts")
    public List<Workout> getAllWorkouts() {
        return workoutService.getWorkouts();
    }

    @PostMapping("/workouts")
    public Workout addWorkout(@RequestBody Workout workout) {
        return workoutService.addWorkout(workout);
    }

    @PutMapping("/update/{id}")
    public Workout updateWorkout(@PathVariable Long id, @RequestBody Workout workout) {
        return workoutService
                .updateWorkoutById(id, workout)
                .orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWorkoutById(@PathVariable Long id) {
        workoutService.deleteWorkoutById(id);
    }

    // Exercises Part

    @PostMapping("/workouts/{workoutId}/exercise")
    public void addExerciseToWorkout(@PathVariable Long workoutId,
                                     @RequestBody Exercise exercise) {
        Workout workout = workoutService.getWorkoutById(workoutId);
        workout.addExercise(exercise);
        workoutService.updateWorkoutById(workoutId, workout);
    }

    @PostMapping("/workouts/{workoutId}/exercise/{exerciseId}")
    public void addSetToExercise(@PathVariable Long workoutId,
                                 @PathVariable Long exerciseId,
                                 @RequestBody Set set) {

        Workout workout = workoutService.getWorkoutById(workoutId);
        workout.getExercises()
                .stream()
                .filter(ex -> ex.getId().equals(exerciseId))
                .findFirst()
                .get()
                .addSet(set);

        workoutService.updateWorkoutById(workoutId, workout);
    }
}
