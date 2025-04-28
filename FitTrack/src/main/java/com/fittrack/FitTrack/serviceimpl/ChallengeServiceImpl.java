package com.fittrack.FitTrack.serviceimpl;

import com.fittrack.FitTrack.exception.ChallengeException;
import com.fittrack.FitTrack.models.Challenge;
import com.fittrack.FitTrack.repository.ChallengeRepository;
import com.fittrack.FitTrack.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    private  final ChallengeRepository challengeRepository;

    @Override
    public Challenge createChallenge(Challenge challenge) throws ChallengeException {
        if (challenge.getStartDate().isAfter(challenge.getEndDate())) {
            throw new ChallengeException("Start date must be before end date.");
        }
        return challengeRepository.save(challenge);
    }

    @Override
    public List<Challenge> getAllChallenges() {
       return challengeRepository.findAll();
    }

    @Override
    public Challenge getChallengeById(Integer challengeId) throws ChallengeException {
        return challengeRepository.findById(challengeId).orElseThrow(()-> new ChallengeException("Challenge not found"));
    }
}
