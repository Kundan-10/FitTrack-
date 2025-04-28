package com.fittrack.FitTrack.serviceimpl;

import com.fittrack.FitTrack.dto.UserDTO;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.repository.UserRepo;
import com.fittrack.FitTrack.service.UserService;
import com.fittrack.FitTrack.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Integer id) throws UserException {
        return userRepo.getReferenceById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User updateUser(UserDTO request) throws UserException {

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(encodedPassword);
        user.setRole(request.getRole());
        return  userRepo.save(user);
    }

    @Override
    public String deleteUser(Integer userId) throws UserException {
       User user= jwtUtils.getAuthenticatedUser();
        if (!Objects.equals(user.getId(), userId)) {
            throw new UserException("Unauthorized access: You can only delete your own account.");
        }
        userRepo.delete(user);
        return String.format("User account with ID %d has been deleted successfully. We're sad to see you go!", userId);
    }
}
