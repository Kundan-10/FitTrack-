package com.fittrack.FitTrack.controller;

import com.fittrack.FitTrack.exception.ChallengeException;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.ChallengeParticipation;
import com.fittrack.FitTrack.service.ChallengeParticipationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/participations")
@Tag(name = "Challenge Participation", description = "Endpoints for participating in fitness challenges")
public class ChallengeParticipationController {

    private final ChallengeParticipationService challengeParticipationService;

    @Operation(
            summary = "Join a Challenge",
            description = "Allows a logged-in user to join a specific challenge by ID"
    )
    @PostMapping("/{challengeId}")
    public ResponseEntity<ChallengeParticipation> joinChallenge(@PathVariable Integer challengeId)
            throws ChallengeException, UserException {
        ChallengeParticipation participation = challengeParticipationService.joinChallenge(challengeId);
        return new ResponseEntity<>(participation, HttpStatus.CREATED); // <-- 201 Created
    }

    @Operation(
            summary = "Get User Participations",
            description = "Fetch all challenges that the authenticated user has joined"
    )
    @GetMapping
    public ResponseEntity<List<ChallengeParticipation>> getUserParticipations() throws UserException {
        List<ChallengeParticipation> participations = challengeParticipationService.getUserParticipations();
        return new ResponseEntity<>(participations, HttpStatus.OK); // <-- 200 OK
    }

    @Operation(
            summary = "Update Participation Progress",
            description = "Update the progress and calories burned for a particular participation ID"
    )
    @PutMapping("/{participationId}")
    public ResponseEntity<ChallengeParticipation> updateProgress(
            @PathVariable Integer participationId,
            @RequestParam int additionalProgress,
            @RequestParam int additionalCalories
    ) throws ChallengeException {
        ChallengeParticipation updatedParticipation = challengeParticipationService.updateProgress(participationId, additionalProgress, additionalCalories);
        return new ResponseEntity<>(updatedParticipation, HttpStatus.OK); // <-- 200 OK
    }
}
