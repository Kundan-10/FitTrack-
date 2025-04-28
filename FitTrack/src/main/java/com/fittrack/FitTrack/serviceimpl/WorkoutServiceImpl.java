package com.fittrack.FitTrack.serviceimpl;

import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.models.Workout;
import com.fittrack.FitTrack.repository.WorkoutRepo;
import com.fittrack.FitTrack.service.WorkoutService;
import com.fittrack.FitTrack.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService{

    private  final WorkoutRepo workoutRepo;
    private final JwtUtils jwtUtils;


    @Override
    public Workout logWorkout(Workout workout) throws UserException {
        User currUser = jwtUtils.getAuthenticatedUser();
        workout.setUser(currUser);
        workout.setTimestamp(LocalDateTime.now());
        return workoutRepo.save(workout);
    }

    @Override
    public List<Workout> getWorkoutsByUser(Integer userId) throws UserException {
        User currUser = jwtUtils.getAuthenticatedUser();
        if (!currUser.getId().equals(userId)) {
            throw new UserException("Unauthorized access to workout history.");
        }
        return workoutRepo.findByUserId(userId);
    }
}
