package com.fittrack.FitTrack.controller;

import com.fittrack.FitTrack.dto.AuthenticationRequest;
import com.fittrack.FitTrack.dto.AuthenticationResponse;
import com.fittrack.FitTrack.dto.RefreshTokenRequest;
import com.fittrack.FitTrack.dto.UserDTO;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService AuthenticationService) {
        this.authenticationService = AuthenticationService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account and returns a JWT token")
    public ResponseEntity<User> register(@Valid @RequestBody UserDTO request) {
        User response = authenticationService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate a user", description = "Authenticates a user with provided credentials and returns a JWT token")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    @Operation(summary = "RefreshToken  a user", description = "refresh token JWT token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request){
        AuthenticationResponse response = authenticationService.refreshToken(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
