package com.example.workouttracker.api;

import com.example.workouttracker.model.Exercise;
import com.example.workouttracker.model.Set;
import com.example.workouttracker.model.Workout;
import com.example.workouttracker.service.ExerciseService;
import com.example.workouttracker.service.SetService;
import com.example.workouttracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final SetService setService;

    @Autowired
    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService, SetService setService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.setService = setService;
    }

    @GetMapping("/workouts/{id}")
    public Workout getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }

//    @GetMapping("/workouts/{name}")
//    public Workout getWorkoutByName(@PathVariable String name) {
//        return workoutService.getWorkoutByName(name);
//    }

    @GetMapping("/workouts")
    public List<Workout> getAllFinishedWorkouts() {
        return workoutService.getAllFinishedWorkouts();
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

    @PostMapping("/workouts/{workoutId}/exercise")
    public void addExerciseToWorkout(@PathVariable Long workoutId,
                                     @RequestBody Exercise exercise) {
        Workout workout = workoutService.getWorkoutById(workoutId);
        workout.addExercise(exercise);
        workoutService.updateWorkoutById(workoutId, workout);
    }

    @GetMapping("/workouts/{workoutId}/exercise/{exerciseId}")
    public Exercise getExerciseForWorkout(@PathVariable Long workoutId,
                                      @PathVariable Long exerciseId) {
        return this.workoutService.getExerciseForWorkout(workoutId, exerciseId);
    }

    @GetMapping("/workouts/{workoutId}/exercise/{exerciseId}/sets")
    public List<Set> getSetsForExerciseForWorkout(@PathVariable Long workoutId,
                                                  @PathVariable Long exerciseId) {
        Exercise exerciseForWorkout = this.workoutService.getExerciseForWorkout(workoutId, exerciseId);
        return exerciseForWorkout.getSets();

    }

    @DeleteMapping("/workouts/{workoutId}/exercise/{exerciseId}/set/{setId}")
    public void deleteSetById(@PathVariable Long workoutId,
                              @PathVariable Long exerciseId,
                              @PathVariable Long setId) {
        exerciseService.getExerciseById(exerciseId).deleteSetForExerciseById(setId);
        setService.deleteSetById(setId);
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

    // Add Workout from Template

    @PostMapping("/workouts/addFromTemplate/{workoutTemplateId}")
    public Long addWorkoutFromTemplate(@PathVariable Long workoutTemplateId) {
        return workoutService.addWorkoutFromTemplate(workoutTemplateId).getId();
    }

    @PostMapping("/workouts/saveTime/{workoutId}")
    public Optional<Workout> saveTimeForWorkout(@PathVariable Long workoutId, @RequestBody String time) {
        // TODO: Finishing workout
        return workoutService.saveTimeForWorkout(workoutId, time);
    }

}
