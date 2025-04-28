package com.fittrack.FitTrack.serviceimpl;

import com.fittrack.FitTrack.exception.ChallengeException;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.Challenge;
import com.fittrack.FitTrack.models.ChallengeParticipation;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.repository.ChallengeParticipationRepository;
import com.fittrack.FitTrack.repository.ChallengeRepository;
import com.fittrack.FitTrack.service.ChallengeParticipationService;
import com.fittrack.FitTrack.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChallengeParticipationServiceImpl implements ChallengeParticipationService {
    private final JwtUtils jwtUtils;
    private  final ChallengeParticipationRepository challengeParticipationRepo;
    private final ChallengeRepository challengeRepository;

    @Override
    public ChallengeParticipation joinChallenge(Integer challengeId) throws ChallengeException, UserException {
        User user = jwtUtils.getAuthenticatedUser();

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new ChallengeException("Challenge not found with ID: " + challengeId));

        // Check if user already joined
        Optional<ChallengeParticipation> existingParticipation =
                challengeParticipationRepo.findByUserAndChallenge(user, challenge);

        if (existingParticipation.isPresent()) {
            throw new ChallengeException("You have already joined this challenge.");
        }

        ChallengeParticipation participation = ChallengeParticipation.builder()
                .user(user)
                .challenge(challenge)
                .progress(0)
                .progressUnit("default")
                .totalCaloriesBurned(0)
                .completed(false)
                .joinedAt(LocalDateTime.now())
                .build();

        return challengeParticipationRepo.save(participation);
    }

    @Override
    public List<ChallengeParticipation> getUserParticipations() throws UserException {
        User user = jwtUtils.getAuthenticatedUser();
        return challengeParticipationRepo.findByUser(user);
    }

    @Override
    public ChallengeParticipation updateProgress(Integer participationId, int additionalProgress, int additionalCalories) throws ChallengeException {
        ChallengeParticipation participation = challengeParticipationRepo.findById(participationId)
                .orElseThrow(() -> new ChallengeException("Participation not found with ID: " + participationId));

        if (participation.isCompleted()) {
            throw new ChallengeException("Challenge already completed!");
        }

        participation.setProgress(participation.getProgress() + additionalProgress);
        participation.setTotalCaloriesBurned(participation.getTotalCaloriesBurned() + additionalCalories);

        if (participation.getProgress() >= 100) { // example condition
            participation.setCompleted(true);
            participation.setCompletedAt(LocalDateTime.now());
        }

        return challengeParticipationRepo.save(participation);
    }
}
