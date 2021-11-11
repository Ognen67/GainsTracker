package com.example.workouttracker.service;

import com.example.workouttracker.model.Exercise;
import com.example.workouttracker.model.Workout;
import com.example.workouttracker.model.WorkoutStatus;
import com.example.workouttracker.model.WorkoutTemplate;
import com.example.workouttracker.model.exception.ExerciseNotFoundException;
import com.example.workouttracker.model.exception.WorkoutNotFoundException;
import com.example.workouttracker.repository.ExerciseRepository;
import com.example.workouttracker.repository.WorkoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutTemplatesService workoutTemplatesService;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, WorkoutTemplatesService workoutTemplatesService) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutTemplatesService = workoutTemplatesService;
    }

    public Workout getWorkoutById(Long id) {
        return workoutRepository
                .findById(id)
                .orElse(null);
    }

    public Workout getWorkoutByName(String name) {
        return workoutRepository.findWorkoutByName(name);
    }

    public List<Workout> getAllFinishedWorkouts() {
        return workoutRepository.findWorkoutsByStatus(WorkoutStatus.FINISHED);
    }

    public Workout addWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public void deleteWorkoutById(Long id) {
        workoutRepository.deleteById(id);
    }

    public Optional<Workout> updateWorkoutById(Long id, Workout workout) {
        Optional<Workout> workoutOptional = workoutRepository.findById(id);
        if (workoutOptional.isPresent()) {
            Workout workoutToUpdate = workoutOptional.get();
            updateProperties(workout, workoutToUpdate);
            workoutRepository.save(workoutToUpdate);
        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private void updateProperties(Workout workout, Workout workoutToUpdate) {
        workoutToUpdate.setName(workout.getName());
        workoutToUpdate.setExercises(workout.getExercises());
        workoutToUpdate.setStatus(workout.getStatus());
        workoutToUpdate.setTime(workout.getTime());
    }

    public Exercise getExerciseForWorkout(Long workoutId,
                                          Long exerciseId) {
        Workout workout = this.workoutRepository.findById(workoutId).orElseThrow(() -> new WorkoutNotFoundException(workoutId));
        return workout.getExercises().stream().filter(e -> e.getId().equals(exerciseId)).findFirst().orElseThrow(() -> new ExerciseNotFoundException(exerciseId));
    }

    public Workout addWorkoutFromTemplate(Long workoutTemplateId) {
        WorkoutTemplate workoutTemplate = workoutTemplatesService.getWorkoutById(workoutTemplateId);

        Workout workout = new Workout(workoutTemplate.getName());
        workoutTemplate.getExercises().forEach(workout::addExercise);

        return workoutRepository.save(workout);
    }

    public Optional<Workout> saveTimeForWorkout(Long workoutId, String time) {
        Workout workout = this.workoutRepository.getById(workoutId);

        String[] timeArr = time.split(":");
        String hours = "00";
        String minutes = timeArr[0];
        String seconds = timeArr[1];

        if (Integer.parseInt(minutes) > 59) {
            hours = Integer.toString(Integer.parseInt(minutes) / 60);
            minutes = Integer.toString(Integer.parseInt(minutes) % 60);
        }
        LocalTime t = LocalTime.parse(hours + ":" + minutes + ":" + seconds);

        workout.setTime(t);
        workout.setStatus(WorkoutStatus.FINISHED);
        return updateWorkoutById(workoutId, workout);
    }
}
