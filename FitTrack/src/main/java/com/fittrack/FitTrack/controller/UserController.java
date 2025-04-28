package com.fittrack.FitTrack.controller;

import com.fittrack.FitTrack.dto.UserDTO;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "user Management", description = "Endpoints for Managing the user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get a User by ID", description = "Fetch a user based on the provided user ID.")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) throws UserException {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get all Users", description = "Fetch all users from the system.")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Update User Information", description = "Update the user details.")
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable Integer userId,
            @RequestBody @Valid UserDTO request) throws UserException {
        User updatedUser = userService.updateUser(request);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(summary = "Delete a User", description = "Delete the user with the specified ID.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) throws UserException {
        String response = userService.deleteUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
