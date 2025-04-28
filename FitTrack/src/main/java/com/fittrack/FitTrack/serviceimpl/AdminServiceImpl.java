package com.fittrack.FitTrack.serviceimpl;

import com.fittrack.FitTrack.exception.ChallengeException;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.Challenge;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.repository.ChallengeRepository;
import com.fittrack.FitTrack.repository.UserRepo;
import com.fittrack.FitTrack.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepository;
    private final ChallengeRepository challengeRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String suspendUser(Integer userId) throws UserException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));

        user.setActive(false);  // Assuming you have 'active' field for user status
        userRepository.save(user);

        return "User suspended successfully.";
    }

    @Override
    public Challenge createChallenge(Challenge challenge) throws ChallengeException {
        if (challenge.getStartDate().isAfter(challenge.getEndDate())) {
            throw new ChallengeException("Start date must be before end date.");
        }
        return challengeRepository.save(challenge);
    }

    @Override
    public String deleteChallenge(Integer challengeId) throws ChallengeException {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new ChallengeException("Challenge not found"));
        challengeRepository.delete(challenge);
        return "Challenge deleted successfully.";
    }
}
