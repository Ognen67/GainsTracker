package com.example.workouttracker.api;

import com.example.workouttracker.model.ExerciseTemplate;
import com.example.workouttracker.service.ExerciseTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/exercise-templates")
public class ExerciseTemplatesController {

    private final ExerciseTemplateService exerciseTemplateService;

    public ExerciseTemplatesController(ExerciseTemplateService exerciseTemplateService) {
        this.exerciseTemplateService = exerciseTemplateService;
    }

    @GetMapping()
    public List<ExerciseTemplate> getAllExerciseTemplates() {
        return exerciseTemplateService.getAllExerciseTemplates();
    }

    @PostMapping()
    public ExerciseTemplate getAllExerciseTemplates(@RequestBody ExerciseTemplate exerciseTemplate) {
        return exerciseTemplateService.addExerciseTemplate(exerciseTemplate);
    }
}
