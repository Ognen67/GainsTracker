package com.example.workouttracker.service;

import com.example.workouttracker.model.ExerciseTemplate;
import com.example.workouttracker.model.WorkoutTemplate;
import com.example.workouttracker.repository.ExerciseTemplatesRepository;
import com.example.workouttracker.repository.WorkoutTemplatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseTemplateService {

    private final ExerciseTemplatesRepository exerciseTemplatesRepository;

    public ExerciseTemplateService(ExerciseTemplatesRepository exerciseTemplatesRepository) {
        this.exerciseTemplatesRepository = exerciseTemplatesRepository;
    }

    public List<ExerciseTemplate> getAllExerciseTemplates() {
        return exerciseTemplatesRepository.findAll();
    }

    public ExerciseTemplate addExerciseTemplate(ExerciseTemplate exerciseTemplate) {
        return exerciseTemplatesRepository.save(exerciseTemplate);
    }
}
