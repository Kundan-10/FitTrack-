package com.fittrack.FitTrack.service;

import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.Workout;

import java.util.List;

public interface WorkoutService {

    Workout logWorkout(Workout workout) throws UserException;
    List<Workout> getWorkoutsByUser(Integer userId) throws UserException;

}
