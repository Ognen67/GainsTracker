package com.example.workouttracker.service;

import com.example.workouttracker.model.WorkoutTemplate;
import com.example.workouttracker.repository.WorkoutTemplatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutTemplatesService {

    private final WorkoutTemplatesRepository workoutTemplatesRepository;

    public WorkoutTemplatesService(WorkoutTemplatesRepository workoutTemplatesRepository) {
        this.workoutTemplatesRepository = workoutTemplatesRepository;
    }

    public List<WorkoutTemplate> getAllWorkoutTemplates() {
        return workoutTemplatesRepository.findAll();
    }

    public WorkoutTemplate addWorkoutTemplate(WorkoutTemplate workoutTemplate) {
        return workoutTemplatesRepository.save(workoutTemplate);
    }
}
