package com.fittrack.FitTrack.controller;

import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.Workout;
import com.fittrack.FitTrack.service.WorkoutService;
import com.fittrack.FitTrack.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final JwtUtils jwtUtils;

    @Operation(summary = "Log a new workout", description = "Logs a workout for the authenticated user")
    @ApiResponse(responseCode = "200", description = "Workout logged successfully")
    @PostMapping("/log")
    public ResponseEntity<Workout> logWorkout(@RequestBody Workout workout) throws UserException {
        Workout workout1 = workoutService.logWorkout(workout);
        return new ResponseEntity<>(workout1, HttpStatus.OK);
    }

    @Operation(summary = "Get all workouts", description = "Retrieve all workouts of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Fetched workouts successfully")
    @GetMapping
    public ResponseEntity<List<Workout>> getUserWorkouts() throws UserException {
        Integer userId = jwtUtils.getAuthenticatedUser().getId();
        List<Workout> workouts = workoutService.getWorkoutsByUser(userId);
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }
}
