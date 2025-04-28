package com.fittrack.FitTrack.serviceimpl;

import com.fittrack.FitTrack.enums.Role;
import com.fittrack.FitTrack.exception.ChallengeException;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.Challenge;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.repository.ChallengeRepository;
import com.fittrack.FitTrack.service.ChallengeService;
import com.fittrack.FitTrack.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    private  final ChallengeRepository challengeRepository;
    private final JwtUtils jwtUtils;

    @Override
    public Challenge createChallenge(Challenge challenge) throws ChallengeException, UserException {
        User authUser = jwtUtils.getAuthenticatedUser();
        if(authUser.getRole() != Role.ADMIN) throw new UserException("Unauthorized for user");
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
