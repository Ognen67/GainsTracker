package com.example.workouttracker.service;

import com.example.workouttracker.repository.SetRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SetService {

    private final SetRepository setRepository;

    public SetService(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    @Transactional
    public void deleteSetById(Long setId) {
        setRepository.deleteById(setId);
    }
}
