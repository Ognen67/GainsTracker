package com.example.workouttracker.api;

import com.example.workouttracker.model.Exercise;
import com.example.workouttracker.model.Workout;
import com.example.workouttracker.model.WorkoutTemplate;
import com.example.workouttracker.service.ExerciseTemplateService;
import com.example.workouttracker.service.WorkoutTemplatesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/workout-templates")
public class WorkoutTemplatesController {

    private final WorkoutTemplatesService workoutTemplateService;

    public WorkoutTemplatesController(WorkoutTemplatesService workoutTemplateService) {
        this.workoutTemplateService = workoutTemplateService;
    }

    @GetMapping()
    public List<WorkoutTemplate> getAllWorkoutTemplates() {
        return workoutTemplateService.getAllWorkoutTemplates();
    }

    @PostMapping()
    public WorkoutTemplate addWorkoutTemplate(@RequestBody WorkoutTemplate workoutTemplate) {
        return workoutTemplateService.addWorkoutTemplate(workoutTemplate);
    }

    @PostMapping("/workouts/{workoutId}/exercise")
    public void addExerciseToWorkoutTemplate(@PathVariable Long workoutId,
                                             @RequestBody Exercise exercise) {
        WorkoutTemplate workoutTemplate = workoutTemplateService.getWorkoutById(workoutId);
        workoutTemplate.addExercise(exercise);
        workoutTemplateService.updateWorkoutById(workoutId, workoutTemplate);
    }
}
