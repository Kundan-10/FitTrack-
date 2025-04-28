package com.fittrack.FitTrack.utils;

import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final UserRepo userRepo;

    public User getAuthenticatedUser() throws UserException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserException("Unauthorized request.");
        }
        String email = authentication.getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserException("User not found!"));
    }

}
