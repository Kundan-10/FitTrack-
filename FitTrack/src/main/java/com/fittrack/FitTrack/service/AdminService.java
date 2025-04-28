package com.fittrack.FitTrack.service;

import com.fittrack.FitTrack.exception.ChallengeException;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.Challenge;
import com.fittrack.FitTrack.models.User;

import java.util.List;

public interface AdminService {

    List<User> getAllUsers();

    String suspendUser(Integer userId) throws UserException;

    Challenge createChallenge(Challenge challenge) throws ChallengeException;

    String deleteChallenge(Integer challengeId) throws ChallengeException;
}
