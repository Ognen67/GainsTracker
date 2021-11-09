package com.example.workouttracker.service;

import com.example.workouttracker.model.Workout;
import com.example.workouttracker.model.WorkoutTemplate;
import com.example.workouttracker.repository.WorkoutTemplatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public WorkoutTemplate getWorkoutById(Long id) {
        return workoutTemplatesRepository
                .findById(id)
                .orElse(null);
    }

    public Optional<WorkoutTemplate> updateWorkoutById(Long id, WorkoutTemplate workoutTemplate) {
        Optional<WorkoutTemplate> workoutTemplateOptional = workoutTemplatesRepository.findById(id);
        if (workoutTemplateOptional.isPresent()) {
            WorkoutTemplate workoutToUpdate = workoutTemplateOptional.get();
            updateProperties(workoutTemplate, workoutToUpdate);
            workoutTemplatesRepository.save(workoutToUpdate);
        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private void updateProperties(WorkoutTemplate workoutTemplate, WorkoutTemplate workoutTemplateToUpdate) {
        workoutTemplateToUpdate.setName(workoutTemplate.getName());
        workoutTemplateToUpdate.setExercises(workoutTemplate.getExercises());
    }

    public Optional<WorkoutTemplate> findById(Long workoutTemplateId) {
        return workoutTemplatesRepository.findById(workoutTemplateId);
    }
}
