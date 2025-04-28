package com.fittrack.FitTrack.repository;

import com.fittrack.FitTrack.models.Challenge;
import com.fittrack.FitTrack.models.ChallengeParticipation;
import com.fittrack.FitTrack.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeParticipationRepository extends JpaRepository<ChallengeParticipation, Integer> {
    Optional<ChallengeParticipation> findByUserAndChallenge(User user, Challenge challenge);

    List<ChallengeParticipation> findByUser(User user);
}
