package com.fittrack.FitTrack.controller;

import com.fittrack.FitTrack.exception.ChallengeException;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.Challenge;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Tag(name = "Admin APIs", description = "Admin operations for users and challenges")
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "Get all users", description = "Retrieve a list of all users (Admin only)")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Suspend a user", description = "Suspend a user by user ID")
    @PutMapping("/users/{userId}/suspend")
    public ResponseEntity<String> suspendUser(@PathVariable Integer userId) throws UserException {
        String message = adminService.suspendUser(userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "Create a challenge", description = "Admin can create a new challenge")
    @PostMapping("/challenges")
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) throws ChallengeException {
        Challenge createdChallenge = adminService.createChallenge(challenge);
        return new ResponseEntity<>(createdChallenge, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a challenge", description = "Delete a challenge by its ID")
    @DeleteMapping("/challenges/{challengeId}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Integer challengeId) throws ChallengeException {
        String message = adminService.deleteChallenge(challengeId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
