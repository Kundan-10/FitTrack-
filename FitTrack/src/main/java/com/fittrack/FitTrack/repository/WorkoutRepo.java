package com.fittrack.FitTrack.repository;

import com.fittrack.FitTrack.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WorkoutRepo extends JpaRepository<Workout, Integer> {
    List<Workout> findByUserId(Integer id);

    boolean existsByUserIdAndTimestampAfter(Integer id, LocalDateTime timestamp);
}
