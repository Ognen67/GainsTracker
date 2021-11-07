package com.example.workouttracker.repository;

import com.example.workouttracker.model.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {

    @Transactional
    @Modifying
    public void deleteById(Long setId);
}
