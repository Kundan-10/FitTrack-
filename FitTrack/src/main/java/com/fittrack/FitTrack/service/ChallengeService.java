package com.fittrack.FitTrack.service;

import com.fittrack.FitTrack.exception.ChallengeException;
import com.fittrack.FitTrack.models.Challenge;

import java.util.List;

public interface ChallengeService {

    Challenge createChallenge(Challenge challenge) throws ChallengeException;

    List<Challenge> getAllChallenges();

    Challenge getChallengeById(Integer challengeId) throws ChallengeException;
}
