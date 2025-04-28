package com.fittrack.FitTrack.service;

import com.fittrack.FitTrack.exception.ChallengeException;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.ChallengeParticipation;

import java.util.List;

public interface ChallengeParticipationService {

    ChallengeParticipation joinChallenge(Integer challengeId) throws ChallengeException, UserException;

    List<ChallengeParticipation> getUserParticipations() throws UserException;

    public ChallengeParticipation updateProgress(Integer participationId, int additionalProgress, int additionalCalories) throws ChallengeException;
}
