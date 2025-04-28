package com.fittrack.FitTrack.controller;

import com.fittrack.FitTrack.exception.ChallengeException;
import com.fittrack.FitTrack.models.Challenge;
import com.fittrack.FitTrack.service.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/challenges")
@Tag(name = "Challenge Management", description = "Endpoints for managing fitness challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @Operation(summary = "Create a new Challenge", description = "Create a new fitness challenge with valid start and end dates.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Challenge details")
    })
    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) throws ChallengeException {
        Challenge createdChallenge = challengeService.createChallenge(challenge);
        return new ResponseEntity<>(createdChallenge, HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Get all Challenges", description = "Retrieve a list of all fitness challenges.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenges retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        return new ResponseEntity<>(challenges, HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Get Challenge by ID", description = "Retrieve a fitness challenge by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Challenge not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable Integer id) throws ChallengeException {
        Challenge challenge = challengeService.getChallengeById(id);
        return new ResponseEntity<>(challenge, HttpStatusCode.valueOf(200));

    }
}
