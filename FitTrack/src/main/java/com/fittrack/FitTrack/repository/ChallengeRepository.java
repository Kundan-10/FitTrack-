package com.fittrack.FitTrack.repository;

import com.fittrack.FitTrack.models.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {
}
