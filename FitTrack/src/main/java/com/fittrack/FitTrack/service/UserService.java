package com.fittrack.FitTrack.service;

import com.fittrack.FitTrack.dto.UserDTO;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.User;

import java.util.List;

public interface UserService {

    User getUserById(Integer id) throws UserException;
    List<User> getAllUsers();
    User updateUser(UserDTO user) throws UserException;
    String deleteUser(Integer userId) throws UserException;


}
