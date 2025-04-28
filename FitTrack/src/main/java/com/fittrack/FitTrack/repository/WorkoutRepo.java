package com.fittrack.FitTrack.repository;

import com.fittrack.FitTrack.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepo extends JpaRepository<Workout, Integer> {
    List<Workout> findByUserId(Integer id);
}
